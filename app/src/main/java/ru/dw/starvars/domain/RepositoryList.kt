package ru.dw.starvars.domain

import androidx.lifecycle.LiveData
import ru.dw.starvars.data.room.entity.CharactersEntity
import ru.dw.starvars.viewmodel.list.ListCharactersViewModel

interface RepositoryList {
    fun getRequestUrl(url:String, responseCallBack: ListCharactersViewModel.ResponseCallBackViewModel)
    fun getAllCharacters(): LiveData<List<CharactersEntity>>
}