package ru.dw.starvars.data.repositories.list

import androidx.lifecycle.LiveData
import ru.dw.starvars.data.room.entity.CharactersEntity
import ru.dw.starvars.domain.model.CharactersListResponsePojo

interface DataBaseListLocal {
    fun getAllCharacters(): LiveData<List<CharactersEntity>>
    fun insertDatabaseCharacters(pogo: CharactersListResponsePojo)
    fun refresh()
}