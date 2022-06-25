package ru.dw.starvars.domain.cases



import ru.dw.starvars.data.repositories.RepositoryIpl
import ru.dw.starvars.viewmodel.list.ListPeoplesViewModel

class GetListPeoplesCase(private val repository: RepositoryIpl) {
    fun getListPeoples(url:String, responseCallBackViewModel: ListPeoplesViewModel.ResponseCallBackViewModel){
        repository.getRequestUrl(url,responseCallBackViewModel)
    }
}