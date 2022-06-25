package ru.dw.starvars.viewmodel.list

import androidx.lifecycle.LiveData
import ru.dw.starvars.data.room.PeoplesEntity

interface Repository {
    fun getRequestUrl(url:String, responseCallBack: ListPeoplesViewModel.ResponseCallBackViewModel)
    fun getAll(): LiveData<List<PeoplesEntity>>
}