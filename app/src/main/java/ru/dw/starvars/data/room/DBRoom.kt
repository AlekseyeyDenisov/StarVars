package ru.dw.starvars.data.room

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [
        PeoplesEntity::class,
        AttributesEntity::class,
        //VehiclesEntity::class,
        //SpeciesEntity::class,
       // StarshipsEntity::class
    ], version = 2
)
abstract class DBRoom : RoomDatabase() {
    abstract fun peoplesDao(): PeoplesDao
    abstract fun attrDao(): AttributesDao

}
