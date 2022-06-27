package ru.dw.starvars.data.repositories.list

import androidx.lifecycle.LiveData
import ru.dw.starvars.data.room.entity.PeoplesEntity
import ru.dw.starvars.domain.model.PeoplesListResponsePojo

interface DataBaseListLocal {
    fun getAllPeoples(): LiveData<List<PeoplesEntity>>
    fun insertDatabasePeoples(pogo: PeoplesListResponsePojo)
    fun refresh()
}