package ru.dw.starvars.domain.repository


import ru.dw.starvars.data.room.ValueAttrEntity

interface RepositoryDetails {

    fun getRequestPlanetUrl(url: String, name:(String)->Unit)

    fun getAllValueAttr(): List<ValueAttrEntity>

    fun getValuePlanet(url:String):ValueAttrEntity
}