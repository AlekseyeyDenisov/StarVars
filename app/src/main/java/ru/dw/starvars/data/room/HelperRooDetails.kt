package ru.dw.starvars.data.room

import ru.dw.starvars.MyApp
import ru.dw.starvars.data.repositories.details.DataBaseDetailsLocal

import ru.dw.starvars.data.room.entity.ValueAttrEntity

class HelperRooDetails : DataBaseDetailsLocal {
    private val db: DBRoom = MyApp.dbRoom


    override fun getAllValueAttr(): List<ValueAttrEntity> = db.valueDao().getAll()

    override fun insertValueAttr(valueAttributesEntity: ValueAttrEntity) {
        db.valueDao().insert(valueAttributesEntity)
    }

    override fun getValueAttr(url: String): ValueAttrEntity {
        return db.valueDao().getValueAttr(url)
    }
}