package ru.dw.starvars.data.repositories.list

import androidx.lifecycle.LiveData
import ru.dw.starvars.data.room.PeoplesEntity
import ru.dw.starvars.domain.model.PeoplesListResponsePojo

interface DataBaseLocal {
    fun getAll(): LiveData<List<PeoplesEntity>>
    fun insertUpdateDatabase(pogo: PeoplesListResponsePojo)
    fun refresh()
}