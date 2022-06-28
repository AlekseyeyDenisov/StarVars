package ru.dw.starvars.viewmodel.details


import ru.dw.starvars.data.room.entity.ValueAttrEntity

interface RepositoryDetails {

    fun getRequestUrl(url: String, name:(String)->Unit)

    fun getAllValueAttr(): List<ValueAttrEntity>

    fun getValueAttr(url:String):ValueAttrEntity
}