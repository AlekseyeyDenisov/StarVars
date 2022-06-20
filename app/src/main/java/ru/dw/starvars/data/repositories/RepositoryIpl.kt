package ru.dw.starvars.data.repositories

import ru.dw.starvars.data.retrofit.RetrofitApiStarWars
import ru.dw.starvars.domain.model.PeoplesItemView
import ru.dw.starvars.presenter.list.ListPeoplesViewModel

object RepositoryIpl : Repository {
    private val dataApi = RetrofitApiStarWars


    override fun getPeoples(url: String, responseCallBack: ListPeoplesViewModel.ResponseCallBack) {
        dataApi.getListPeoples(url, object : ListPeoplesViewModel.ResponseCallBack {
            override fun success(listPeoplesItemView: List<PeoplesItemView>) {
                responseCallBack.success(listPeoplesItemView)
            }

            override fun error(error: String) {
                ListStateResponse.Error(error)
            }
        })
    }


}
