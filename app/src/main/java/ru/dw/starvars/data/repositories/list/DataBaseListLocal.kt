package ru.dw.starvars.data.repositories.list

import androidx.lifecycle.LiveData
import ru.dw.starvars.domain.model.CharacterItemView

interface DataBaseListLocal {
    fun getAllCharacters(): LiveData<List<CharacterItemView>>
    fun refresh()
}