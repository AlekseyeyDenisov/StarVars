package ru.dw.starvars.domain.repository

import androidx.lifecycle.LiveData
import ru.dw.starvars.data.room.entity.PeoplesEntity
import ru.dw.starvars.domain.model.PeoplesListResponsePojo
import ru.dw.starvars.presentation.viewmodel.list.ListPeoplesViewModel

interface RepositoryList {
    fun getRequestUrl(url:String, responseCallBack: ListPeoplesViewModel.ResponseCallBackViewModel)
    fun getAllPeoples(): LiveData<List<PeoplesEntity>>
    fun insertDatabasePeoples(pogo: PeoplesListResponsePojo)
    fun refresh()
}