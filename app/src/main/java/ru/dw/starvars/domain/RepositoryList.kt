package ru.dw.starvars.domain

import androidx.lifecycle.LiveData
import ru.dw.starvars.domain.model.CharacterItemView
import ru.dw.starvars.pressentation.view.list.list.ListCharactersViewModel

interface RepositoryList {
    fun getRequestUrl(url:String, responseCallBack: ListCharactersViewModel.ResponseCallBackViewModel)
    fun getAllCharacters(): LiveData<List<CharacterItemView>>
}