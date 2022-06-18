package ru.dw.starvars.data.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [RoomDatabase::class], version = 1)
abstract class DBRoom:RoomDatabase() {
    abstract fun peoplesDao():PeoplesDao
}