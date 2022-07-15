package ru.dw.starvars.data.repositories

import androidx.lifecycle.LiveData
import ru.dw.starvars.data.network.model.PeoplesListResponsePojo
import ru.dw.starvars.data.database.model.PeopleDbModel
import ru.dw.starvars.data.database.model.ValueAttrEntity

interface LocalBase {
    fun getAllPeoples(): LiveData<List<PeopleDbModel>>
    fun insertDatabasePeoples(pogo: PeoplesListResponsePojo)
    fun getAllValueAttr(): List<ValueAttrEntity>
    fun insertValueAttr(valueAttributesEntity: ValueAttrEntity)
    fun getValuePlanet(url:String): ValueAttrEntity
    fun refresh()
}