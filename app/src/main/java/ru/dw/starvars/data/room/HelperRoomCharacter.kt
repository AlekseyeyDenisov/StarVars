package ru.dw.starvars.data.room


import androidx.lifecycle.LiveData
import ru.dw.starvars.MyApp
import ru.dw.starvars.data.repositories.list.DataBaseListLocal
import ru.dw.starvars.data.room.entity.CharactersEntity
import ru.dw.starvars.domain.model.CharactersListResponsePojo
import ru.dw.starvars.domain.model.ResultsItem
import ru.dw.starvars.utils.VIEW_TAPE_LOAD_MORE
import ru.dw.starvars.utils.convertPogoToEntity


class HelperRoomCharacter : DataBaseListLocal  {
    private val db:DBRoom = MyApp.dbRoom


    override fun refresh() {
        db.chaptersDao().deleteAll()
        db.valueDao().deleteAllAttr()
    }

    override fun getAllCharacters(): LiveData<List<CharactersEntity>> = db.chaptersDao().getAll()

    override fun insertDatabaseCharacters(pogo: CharactersListResponsePojo) {
        Thread {
            db.chaptersDao().deleteNextPage(VIEW_TAPE_LOAD_MORE)

            pogo.results?.forEach { item ->
                if (item != null) {
                    insertItem(item)
                }
            }
            if (pogo.next?.isNotEmpty() == true) {
                db.chaptersDao().insert(crateLoadMore(pogo))
            }
        }.start()
    }

    private fun crateLoadMore(pogo: CharactersListResponsePojo): CharactersEntity {
        return CharactersEntity(0, viewTape = VIEW_TAPE_LOAD_MORE, nextPage = pogo.next)
    }

    private fun insertItem(item: ResultsItem) {
        db.chaptersDao().insert(convertPogoToEntity(item))

    }


}