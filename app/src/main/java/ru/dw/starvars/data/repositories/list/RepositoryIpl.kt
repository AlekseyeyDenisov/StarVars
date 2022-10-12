package ru.dw.starvars.data.repositories.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import ru.dw.starvars.MyApp
import ru.dw.starvars.data.mapper.DataMapper
import ru.dw.starvars.data.retrofit.RetrofitApiStarWars
import ru.dw.starvars.data.room.DBRoom
import ru.dw.starvars.data.room.entity.CharactersDBModel
import ru.dw.starvars.domain.RepositoryList
import ru.dw.starvars.domain.model.CharacterItemView
import ru.dw.starvars.domain.model.CharactersListResponsePojo
import ru.dw.starvars.domain.model.ResultsItem
import ru.dw.starvars.utils.VIEW_TAPE_LOAD_MORE


class RepositoryIpl : RepositoryList, DataBaseListLocal {
    private val dataApi: ApiRetrofitListInterface = RetrofitApiStarWars
    private val mapper = DataMapper()
    private val db: DBRoom = MyApp.dbRoom

    override fun getAllCharacters(): LiveData<List<CharacterItemView>> {
        return Transformations.map(db.chaptersDao().getAll()) { list ->
            list.map {
                mapper.mapperPeoplesDBModelToPeoplesItemView(it)
            }
        }

    }


    override fun refresh() {
        db.chaptersDao().deleteAll()
        db.valueDao().deleteAllAttr()
    }


    override fun getRequestUrl(url: String, requestError: (String) -> Unit) {
        dataApi.getListRequestUrl(
            url,
            object : RetrofitApiStarWars.CallBackRetrofit<CharactersListResponsePojo> {

                override fun success(pogo: CharactersListResponsePojo) {
                    insertDatabaseCharacters(pogo)
                }

                override fun error(error: String) {
                    requestError(error)
                }
            })
    }

    private fun insertDatabaseCharacters(pogo: CharactersListResponsePojo) {
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

    private fun crateLoadMore(pogo: CharactersListResponsePojo): CharactersDBModel {
        return CharactersDBModel(0, viewTape = VIEW_TAPE_LOAD_MORE, nextPage = pogo.next)
    }

    private fun insertItem(item: ResultsItem) {
        db.chaptersDao().insert(mapper.mapperPogoToEntity(item))

    }

}
