package ru.dw.starvars.domain.repository

import androidx.lifecycle.LiveData
import ru.dw.starvars.data.retrofit.model.PeoplesListResponsePojo
import ru.dw.starvars.data.room.PeopleDbModel
import ru.dw.starvars.presentation.viewmodel.list.ListPeoplesViewModel

interface RepositoryList {
    fun getRequestListUrl(url:String, responseCallBack: ListPeoplesViewModel.ResponseCallBackViewModel)
    fun getAllPeoples(): LiveData<List<PeopleDbModel>>
    fun insertDatabasePeoples(pogo: PeoplesListResponsePojo)
    fun refresh()
}