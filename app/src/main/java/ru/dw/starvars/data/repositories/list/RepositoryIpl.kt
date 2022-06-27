package ru.dw.starvars.data.repositories.list

import androidx.lifecycle.LiveData
import ru.dw.starvars.data.retrofit.RetrofitApiStarWars
import ru.dw.starvars.data.room.HelperRoomPeople
import ru.dw.starvars.data.room.entity.PeoplesEntity

import ru.dw.starvars.domain.model.PeoplesListResponsePojo
import ru.dw.starvars.viewmodel.list.ListPeoplesViewModel
import ru.dw.starvars.viewmodel.list.RepositoryList


class RepositoryIpl : RepositoryList, DataBaseListLocal {
    private val dataApi: ApiRetrofitListInterface = RetrofitApiStarWars
    private val dataRoom:DataBaseListLocal = HelperRoomPeople()

    override fun getAllPeoples(): LiveData<List<PeoplesEntity>> = dataRoom.getAllPeoples()

    override fun insertDatabasePeoples(pogo: PeoplesListResponsePojo) {
        dataRoom.insertDatabasePeoples(pogo)
    }

    override fun refresh() = dataRoom.refresh()


    override fun getRequestUrl(
        url: String,
        responseCallBack: ListPeoplesViewModel.ResponseCallBackViewModel
    ) {
        dataApi.getListRequestUrl(url, object : RetrofitApiStarWars.CallBackRetrofit<PeoplesListResponsePojo> {


            override fun success(pogo: PeoplesListResponsePojo) {
                insertDatabasePeoples(pogo)
            }

            override fun error(error: String) {
                responseCallBack.error(error)
            }
        })
    }

}
