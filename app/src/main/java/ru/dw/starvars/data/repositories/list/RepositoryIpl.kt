package ru.dw.starvars.data.repositories.list

import androidx.lifecycle.LiveData
import ru.dw.starvars.data.retrofit.RetrofitApiStarWars
import ru.dw.starvars.data.room.HelperRoomCharacter
import ru.dw.starvars.data.room.entity.CharactersEntity
import ru.dw.starvars.domain.RepositoryList
import ru.dw.starvars.domain.model.CharactersListResponsePojo
import ru.dw.starvars.viewmodel.list.ListCharactersViewModel


class RepositoryIpl : RepositoryList, DataBaseListLocal {
    private val dataApi: ApiRetrofitListInterface = RetrofitApiStarWars
    private val dataRoom:DataBaseListLocal = HelperRoomCharacter()

    override fun getAllCharacters(): LiveData<List<CharactersEntity>> = dataRoom.getAllCharacters()

    override fun insertDatabaseCharacters(pogo: CharactersListResponsePojo) {
        dataRoom.insertDatabaseCharacters(pogo)
    }

    override fun refresh() = dataRoom.refresh()


    override fun getRequestUrl(
        url: String,
        responseCallBack: ListCharactersViewModel.ResponseCallBackViewModel
    ) {
        dataApi.getListRequestUrl(url, object : RetrofitApiStarWars.CallBackRetrofit<CharactersListResponsePojo> {

            override fun success(pogo: CharactersListResponsePojo) {
                insertDatabaseCharacters(pogo)
            }

            override fun error(error: String) {
                responseCallBack.error(error)
            }
        })
    }

}
