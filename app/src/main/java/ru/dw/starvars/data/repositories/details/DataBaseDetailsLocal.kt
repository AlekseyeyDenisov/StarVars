package ru.dw.starvars.data.repositories.details

import androidx.lifecycle.LiveData
import ru.dw.starvars.data.room.entity.AttributesEntity
import ru.dw.starvars.data.room.entity.PeoplesEntity
import ru.dw.starvars.data.room.entity.ValueAttrEntity
import ru.dw.starvars.domain.model.PeoplesListResponsePojo

interface DataBaseDetailsLocal {
    fun getAllAttr(): List<AttributesEntity>
    fun getAllValueAttr(): List<ValueAttrEntity>
    fun insertValueAttr(valueAttributesEntity: ValueAttrEntity)
    fun getValueAttr(url:String):ValueAttrEntity

}