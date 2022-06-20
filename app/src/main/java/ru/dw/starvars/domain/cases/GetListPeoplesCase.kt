package ru.dw.starvars.domain.cases


import ru.dw.starvars.data.repositories.RepositoryIpl
import ru.dw.starvars.presenter.list.ListPeoplesViewModel

class GetListPeoplesCase(private val repository: RepositoryIpl) {
    fun getListPeoples(url:String,responseCallBack: ListPeoplesViewModel.ResponseCallBack){
        repository.getPeoples(url,responseCallBack)
    }
}