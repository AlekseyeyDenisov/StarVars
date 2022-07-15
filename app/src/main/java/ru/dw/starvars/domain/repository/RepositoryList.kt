package ru.dw.starvars.domain.repository

import androidx.lifecycle.LiveData
import ru.dw.starvars.data.network.model.PeoplesListResponsePojo
import ru.dw.starvars.domain.model.PeoplesItemView
import ru.dw.starvars.presentation.viewmodel.list.ListPeoplesViewModel

interface RepositoryList {
    fun getRequestListUrl(url:String, responseCallBack: ListPeoplesViewModel.ResponseCallBackViewModel)
    fun getAllPeoples(): LiveData<List<PeoplesItemView>>
    fun insertDatabasePeoples(pogo: PeoplesListResponsePojo)
    fun refresh()
}