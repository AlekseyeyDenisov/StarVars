package ru.dw.starvars.data.repositories

import ru.dw.starvars.data.retrofit.RetrofitApiStarWars
import ru.dw.starvars.domain.model.PeoplesItemView
import ru.dw.starvars.domain.model.PeoplesItemView.Companion.VIEW_TAPE_LOAD_MORE
import ru.dw.starvars.presenter.list.ListPeoplesViewModel

object RepositoryIpl : Repository {
    private val dataApi = RetrofitApiStarWars
    private val listPeoples = mutableListOf<PeoplesItemView>()


    override fun getPeoples(url: String, responseCallBack: ListPeoplesViewModel.ResponseCallBack) {
        dataApi.getListPeoples(url, object : ListPeoplesViewModel.ResponseCallBack {
            override fun success(listPeoplesItemView: List<PeoplesItemView>) {
                 listPeoples.removeIf {
                     it.viewTape == VIEW_TAPE_LOAD_MORE
                 }
                listPeoples.addAll(listPeoplesItemView)
                responseCallBack.success(listPeoples)
            }

            override fun error(error: String) {
                ListStateResponse.Error(error)
            }
        })
    }


}
