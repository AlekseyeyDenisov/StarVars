package ru.dw.starvars.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.dw.starvars.data.room.entity.CharactersEntity
import ru.dw.starvars.data.room.entity.ValueAttrEntity


@Database(
    entities = [
        CharactersEntity::class,
        ValueAttrEntity::class
    ], version = 1
)
abstract class DBRoom : RoomDatabase() {
    abstract fun chaptersDao(): CharactersDao
    abstract fun valueDao(): ValueAttrDao

}
