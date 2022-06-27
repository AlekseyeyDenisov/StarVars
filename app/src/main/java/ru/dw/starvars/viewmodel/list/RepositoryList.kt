package ru.dw.starvars.viewmodel.list

import androidx.lifecycle.LiveData
import ru.dw.starvars.data.room.entity.PeoplesEntity
import ru.dw.starvars.viewmodel.list.ListPeoplesViewModel

interface RepositoryList {
    fun getRequestUrl(url:String, responseCallBack: ListPeoplesViewModel.ResponseCallBackViewModel)
    fun getAllPeoples(): LiveData<List<PeoplesEntity>>
}