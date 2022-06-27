package ru.dw.starvars.data.repositories.details


import ru.dw.starvars.data.room.entity.ValueAttrEntity


interface DataBaseDetailsLocal {
    fun getAllValueAttr(): List<ValueAttrEntity>
    fun insertValueAttr(valueAttributesEntity: ValueAttrEntity)
    fun getValueAttr(url:String):ValueAttrEntity

}