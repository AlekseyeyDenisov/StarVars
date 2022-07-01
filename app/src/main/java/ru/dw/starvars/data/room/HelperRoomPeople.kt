package ru.dw.starvars.data.room


import androidx.lifecycle.LiveData
import ru.dw.starvars.MyApp
import ru.dw.starvars.data.repositories.LocalBase
import ru.dw.starvars.data.room.entity.PeoplesEntity
import ru.dw.starvars.data.room.entity.ValueAttrEntity
import ru.dw.starvars.data.retrofit.model.PeoplesListResponsePojo
import ru.dw.starvars.data.retrofit.model.ResultsItem
import ru.dw.starvars.utils.Utils


class HelperRoomPeople : LocalBase {
    private val db: DBRoom = MyApp.dbRoom


    fun refresh() {
        db.peoplesDao().deleteAll()
        db.valueDao().deleteAllAttr()
    }

    override fun getAllPeoples(): LiveData<List<PeoplesEntity>> = db.peoplesDao().getAll()

    override fun insertDatabasePeoples(pogo: PeoplesListResponsePojo) {
        Thread {
            db.peoplesDao().deleteNextPage(Utils.VIEW_TAPE_LOAD_MORE)

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
        return PeoplesEntity(0, viewTape = Utils.VIEW_TAPE_LOAD_MORE, nextPage = pogo.next)
    }

    private fun insertItem(item: ResultsItem) {
        db.peoplesDao().insert(Utils.convertPogoToEntity(item))

    }
    override fun getAllValueAttr(): List<ValueAttrEntity> = db.valueDao().getAll()

    override fun insertValueAttr(valueAttributesEntity: ValueAttrEntity) {
        db.valueDao().insert(valueAttributesEntity)
    }

    override fun getValueAttr(url: String): ValueAttrEntity {
        return db.valueDao().getValueAttr(url)
    }


}