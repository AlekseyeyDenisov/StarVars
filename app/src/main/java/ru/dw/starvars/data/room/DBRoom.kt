package ru.dw.starvars.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.dw.starvars.data.room.entity.PeoplesEntity
import ru.dw.starvars.data.room.entity.ValueAttrEntity


@Database(
    entities = [
        PeoplesEntity::class,
        ValueAttrEntity::class
    ], version = 1
)
abstract class DBRoom : RoomDatabase() {
    abstract fun peoplesDao(): PeoplesDao
    abstract fun valueDao(): ValueAttrDao

}
