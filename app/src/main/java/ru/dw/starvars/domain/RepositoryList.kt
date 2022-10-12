package ru.dw.starvars.domain

import androidx.lifecycle.LiveData
import ru.dw.starvars.domain.model.CharacterItemView

interface RepositoryList {
    fun getRequestUrl(url:String, requestError: (String) -> Unit)
    fun getAllCharacters(): LiveData<List<CharacterItemView>>
}