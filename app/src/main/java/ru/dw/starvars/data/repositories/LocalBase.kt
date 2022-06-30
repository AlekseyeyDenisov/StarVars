package ru.dw.starvars.data.repositories

import androidx.lifecycle.LiveData
import ru.dw.starvars.data.room.entity.PeoplesEntity
import ru.dw.starvars.data.room.entity.ValueAttrEntity
import ru.dw.starvars.domain.model.PeoplesListResponsePojo

interface LocalBase {
    fun getAllPeoples(): LiveData<List<PeoplesEntity>>
    fun insertDatabasePeoples(pogo: PeoplesListResponsePojo)
    fun getAllValueAttr(): List<ValueAttrEntity>
    fun insertValueAttr(valueAttributesEntity: ValueAttrEntity)
    fun getValueAttr(url:String): ValueAttrEntity
}