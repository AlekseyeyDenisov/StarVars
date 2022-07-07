package ru.dw.starvars.data.room


import androidx.lifecycle.LiveData
import ru.dw.starvars.MyApp
import ru.dw.starvars.data.repositories.LocalBase
import ru.dw.starvars.data.retrofit.model.PeoplesListResponsePojo
import ru.dw.starvars.data.retrofit.model.ResultsItem
import ru.dw.starvars.utils.Utils


class HelperRoomPeople : LocalBase {
    private val db: AppDataBase = MyApp.dbRoom


    override fun refresh() {
        db.peoplesDao().deleteAll()
        db.valueDao().deleteAllAttr()
    }

    override fun getAllPeoples(): LiveData<List<PeopleDbModel>> = db.peoplesDao().getAll()

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

    override fun getAllValueAttr(): List<ValueAttrEntity> = db.valueDao().getAll()

    override fun insertValueAttr(valueAttributesEntity: ValueAttrEntity) {
        db.valueDao().insert(valueAttributesEntity)
    }

    override fun getValuePlanet(url: String): ValueAttrEntity {
        return db.valueDao().getValuePlanet(url)
    }

    private fun crateLoadMore(pogo: PeoplesListResponsePojo): PeopleDbModel {
        return PeopleDbModel(0, viewTape = Utils.VIEW_TAPE_LOAD_MORE, nextPage = pogo.next)
    }

    private fun insertItem(item: ResultsItem) {
        db.peoplesDao().insert(PeopleListMapper().mapPogoToEntity(item))

    }


}