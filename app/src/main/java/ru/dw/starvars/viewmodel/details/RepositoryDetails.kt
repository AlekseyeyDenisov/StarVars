package ru.dw.starvars.viewmodel.details

import ru.dw.starvars.data.room.entity.AttributesEntity
import ru.dw.starvars.data.room.entity.ValueAttrEntity

interface RepositoryDetails {

    fun getRequestUrl(url: String,attr: String, name:(String)->Unit)

    fun getAllAttr(): List<AttributesEntity>

    fun getAllValueAttr(): List<ValueAttrEntity>

    fun getValueAttr(url:String):ValueAttrEntity
}