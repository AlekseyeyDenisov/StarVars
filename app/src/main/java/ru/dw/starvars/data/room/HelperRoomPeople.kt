package ru.dw.starvars.data.room


import androidx.lifecycle.LiveData
import ru.dw.starvars.MyApp
import ru.dw.starvars.data.repositories.list.DataBaseListLocal
import ru.dw.starvars.data.room.entity.PeoplesEntity
import ru.dw.starvars.domain.model.PeoplesListResponsePojo
import ru.dw.starvars.domain.model.ResultsItem
import ru.dw.starvars.utils.*


class HelperRoomPeople : DataBaseListLocal  {
    private val db:DBRoom = MyApp.dbRoom


    override fun refresh() {
        db.peoplesDao().deleteAll()
        db.valueDao().deleteAllAttr()
    }

    override fun getAllPeoples(): LiveData<List<PeoplesEntity>> = db.peoplesDao().getAll()

    override fun insertDatabasePeoples(pogo: PeoplesListResponsePojo) {
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
        db.peoplesDao().insert(convertPogoToEntity(item))

    }


}