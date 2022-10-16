package ru.dw.starvars.data.repositories.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import ru.dw.starvars.data.repositories.mapper.DataMapper
import ru.dw.starvars.data.api.ApiService
import ru.dw.starvars.data.database.AppDataBase
import ru.dw.starvars.data.database.entity.CharactersDBModel
import ru.dw.starvars.domain.RepositoryList
import ru.dw.starvars.domain.model.CharacterItemView
import ru.dw.starvars.domain.model.CharactersListResponsePojo
import ru.dw.starvars.domain.model.ResultsItem
import ru.dw.starvars.utils.VIEW_TAPE_LOAD_MORE
import javax.inject.Inject


class RepositoryListIpl @Inject constructor(
    private val mapper: DataMapper,
    private val dbDao: AppDataBase,
    private val dataApi: ApiRetrofitListInterface
) : RepositoryList {




    override fun getAllCharacters(): LiveData<List<CharacterItemView>> {
        return Transformations.map(dbDao.chaptersDao().getAll()) { list ->
            list.map {
                mapper.mapperPeoplesDBModelToPeoplesItemView(it)
            }
        }

    }


    override fun refresh() {
        dbDao.chaptersDao().deleteAll()
        dbDao.valueDao().deleteAllAttr()
    }


    override fun getRequestUrl(url: String, requestError: (String) -> Unit) {
        dataApi.getListRequestUrl(
            url,
            object : ApiService.CallBackRetrofit<CharactersListResponsePojo> {

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
            dbDao.chaptersDao().deleteNextPage(VIEW_TAPE_LOAD_MORE)

            pogo.results?.forEach { item ->
                if (item != null) {
                    insertItem(item)
                }
            }
            if (pogo.next?.isNotEmpty() == true) {
                dbDao.chaptersDao().insert(crateLoadMore(pogo))
            }
        }.start()
    }

    private fun crateLoadMore(pogo: CharactersListResponsePojo): CharactersDBModel {
        return CharactersDBModel(0, viewTape = VIEW_TAPE_LOAD_MORE, nextPage = pogo.next)
    }

    private fun insertItem(item: ResultsItem) {
        dbDao.chaptersDao().insert(mapper.mapperPogoToEntity(item))

    }

}
