package ru.dw.starvars.data.repositories

import ru.dw.starvars.MyApp
import ru.dw.starvars.data.retrofit.RetrofitApiStarWars
import ru.dw.starvars.domain.model.PeoplesItemView
import ru.dw.starvars.presenter.list.ListPeoplesViewModel

object RepositoryIpl : Repository {
    private val dataApi:Repository = RetrofitApiStarWars
    //private val dataRoom = MyApp.getDBRoom()


    override fun getPeoples(url: String, responseCallBack: ListPeoplesViewModel.ResponseCallBack) {
        dataApi.getPeoples(url, object : ListPeoplesViewModel.ResponseCallBack {
            override fun success(listPeoplesItemView: List<PeoplesItemView>) {
                responseCallBack.success(listPeoplesItemView)
                //dataRoom.insertUpdateDatabase(listPeoplesItemView)

            }

            override fun error(error: String) {
                responseCallBack.error(error)
            }
        })
    }


}
