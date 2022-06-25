package ru.dw.starvars.data.repositories

import androidx.lifecycle.LiveData
import ru.dw.starvars.MyApp
import ru.dw.starvars.data.retrofit.RetrofitApiStarWars
import ru.dw.starvars.data.room.PeoplesEntity
import ru.dw.starvars.domain.model.PeoplesListResponsePojo
import ru.dw.starvars.viewmodel.list.ListPeoplesViewModel
import ru.dw.starvars.viewmodel.list.Repository


object RepositoryIpl : Repository, DataBaseLocal {
    private val dataApi:ApiStarWarsRetrofit = RetrofitApiStarWars
    private val dataRoom:DataBaseLocal = MyApp.getDBRoom()

    override fun getAll(): LiveData<List<PeoplesEntity>> = dataRoom.getAll()

    override fun insertUpdateDatabase(pogo: PeoplesListResponsePojo) {
        dataRoom.insertUpdateDatabase(pogo)
    }

    override fun refresh() = dataRoom.refresh()


    override fun getRequestUrl(
        url: String,
        responseCallBack: ListPeoplesViewModel.ResponseCallBackViewModel
    ) {
        dataApi.getRequestUrl(url, object : RetrofitApiStarWars.ResponseCallBackRetrofit {


            override fun success(pogo: PeoplesListResponsePojo) {
                insertUpdateDatabase(pogo)
            }

            override fun error(error: String) {
                responseCallBack.error(error)
            }
        })
    }

}
