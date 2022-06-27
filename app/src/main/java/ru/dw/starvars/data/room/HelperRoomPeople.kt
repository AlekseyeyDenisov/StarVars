package ru.dw.starvars.data.room


import androidx.lifecycle.LiveData
import androidx.room.Room
import ru.dw.starvars.MyApp
import ru.dw.starvars.data.repositories.list.DataBaseLocal
import ru.dw.starvars.data.room.entity.AttributesEntity
import ru.dw.starvars.data.room.entity.PeoplesEntity
import ru.dw.starvars.domain.model.PeoplesListResponsePojo
import ru.dw.starvars.domain.model.ResultsItem
import ru.dw.starvars.utils.*


class HelperRoomPeople : DataBaseLocal {
    private val db:DBRoom = MyApp.dbRoom




    override fun refresh() {
        db.peoplesDao().deleteAll()
        db.attrDao().deleteAllAttr()
    }

    override fun getAll(): LiveData<List<PeoplesEntity>> = db.peoplesDao().getAll()

    override fun insertUpdateDatabase(pogo: PeoplesListResponsePojo) {
        Thread {
            db.peoplesDao().deleteNextPage(VIEW_TAPE_LOAD_MORE)

            pogo.results?.forEach { item ->
                if (item != null) {
                    insertItem(item)
                }
            }
            if (pogo.next?.isNotEmpty() == true) {
                db.peoplesDao().insert(crateLoadMore(pogo))
            }
        }.start()
    }

    private fun crateLoadMore(pogo: PeoplesListResponsePojo): PeoplesEntity {
        return PeoplesEntity(0, viewTape = VIEW_TAPE_LOAD_MORE, nextPage = pogo.next)
    }

    private fun insertItem(item: ResultsItem) {
        val idPeoples: Long = db.peoplesDao().insert(convertPogoToEntity(item))
        insertAttr(idPeoples, item)
    }

    private fun insertAttr(idPeoples: Long, item: ResultsItem) {
        insertAttrDB(item.films as List<String>, idPeoples, CONSTANT_ATTRIBUTE_FILMS)

        insertAttrDB(item.species as List<String>, idPeoples, CONSTANT_ATTRIBUTE_SPECIES)

        insertAttrDB(item.vehicles as List<String>, idPeoples, CONSTANT_ATTRIBUTE_VEHICLES)

        insertAttrDB(item.starships as List<String>, idPeoples, CONSTANT_ATTRIBUTE_STARSHIPS)

    }

    private fun insertAttrDB(list: List<String>, idPeoples: Long, constantAttr: String) {
        if (list.isNotEmpty()) {
            list.forEach { url ->
                val attributesEntity = AttributesEntity(0, idPeoples, constantAttr, url)
                db.attrDao().insert(attributesEntity)
            }
        }
    }

}