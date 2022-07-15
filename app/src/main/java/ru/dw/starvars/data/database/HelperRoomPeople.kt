package ru.dw.starvars.data.database


import androidx.lifecycle.LiveData
import ru.dw.starvars.MyApp
import ru.dw.starvars.data.database.model.PeopleDbModel
import ru.dw.starvars.data.database.model.ValueAttrEntity
import ru.dw.starvars.data.mapper.StarWarsMapper
import ru.dw.starvars.data.repositories.LocalBase
import ru.dw.starvars.data.network.model.PeoplesListResponsePojo
import ru.dw.starvars.data.network.model.ResultsItem
import ru.dw.starvars.utils.Utils
import kotlin.concurrent.thread


class HelperRoomPeople : LocalBase {
    private val db: AppDataBase = MyApp.dbRoom


    override fun refresh() {
        db.peoplesDao().deleteAll()
        db.valueDao().deleteAllAttr()
    }

    override fun getAllPeoples(): LiveData<List<PeopleDbModel>> = db.peoplesDao().getAll()

    override fun insertDatabasePeoples(pogo: PeoplesListResponsePojo) {
        db.peoplesDao().deleteNextPage(Utils.VIEW_TAPE_LOAD_MORE)
        pogo.results?.forEach { item ->
            if (item != null) {
                insertItem(item)
            }
        }
        if (pogo.next?.isNotEmpty() == true) {
            db.peoplesDao().insert(crateLoadMore(pogo))
        }
    }

    override fun getAllValueAttr(): List<ValueAttrEntity> = db.valueDao().getAll()

    override fun insertValueAttr(valueAttributesEntity: ValueAttrEntity) {
        thread {
            db.valueDao().insert(valueAttributesEntity)
        }
    }

    override fun getValuePlanet(url: String): ValueAttrEntity {
        return db.valueDao().getValuePlanet(url)
    }

    private fun crateLoadMore(pogo: PeoplesListResponsePojo): PeopleDbModel {
        return PeopleDbModel(0, viewTape = Utils.VIEW_TAPE_LOAD_MORE, nextPage = pogo.next)
    }

    private fun insertItem(item: ResultsItem) {
        db.peoplesDao().insert(StarWarsMapper().mapPogoToEntity(item))

    }


}