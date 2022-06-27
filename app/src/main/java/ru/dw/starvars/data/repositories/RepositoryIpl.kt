package ru.dw.starvars.data.repositories

import androidx.lifecycle.LiveData
import ru.dw.starvars.data.repositories.list.ApiRetrofitListInterface
import ru.dw.starvars.data.repositories.list.DataBaseLocal
import ru.dw.starvars.data.retrofit.RetrofitApiStarWars
import ru.dw.starvars.data.room.HelperRoomPeople
import ru.dw.starvars.data.room.entity.PeoplesEntity

import ru.dw.starvars.domain.model.PeoplesListResponsePojo
import ru.dw.starvars.viewmodel.list.ListPeoplesViewModel
import ru.dw.starvars.viewmodel.list.Repository


class RepositoryIpl : Repository, DataBaseLocal {
    private val dataApi: ApiRetrofitListInterface = RetrofitApiStarWars
    private val dataRoom:DataBaseLocal = HelperRoomPeople()

    override fun getAll(): LiveData<List<PeoplesEntity>> = dataRoom.getAll()

    override fun insertUpdateDatabase(pogo: PeoplesListResponsePojo) {
        dataRoom.insertUpdateDatabase(pogo)
    }

    override fun refresh() = dataRoom.refresh()


    override fun getRequestUrl(
        url: String,
        responseCallBack: ListPeoplesViewModel.ResponseCallBackViewModel
    ) {
        dataApi.getListRequestUrl(url, object : RetrofitApiStarWars.GenericCallBackRetrofit<PeoplesListResponsePojo> {


            override fun success(pogo: PeoplesListResponsePojo) {
                insertUpdateDatabase(pogo)
            }

            override fun error(error: String) {
                responseCallBack.error(error)
            }
        })
    }

}
