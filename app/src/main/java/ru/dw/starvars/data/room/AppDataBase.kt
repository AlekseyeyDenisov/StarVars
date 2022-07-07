package ru.dw.starvars.data.room

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database( entities = [PeopleDbModel::class, ValueAttrEntity::class], version = 1
)
abstract class AppDataBase : RoomDatabase() {
    abstract fun peoplesDao(): PeoplesDao
    abstract fun valueDao(): ValuePlanetDao

    companion object {
        private var INSTANCE: AppDataBase? = null
        private val LOCK = Any()
        private const val DB_MAME = "shop_item.db"

        fun getInstance(application: Application): AppDataBase {
            INSTANCE?.let {
                return it
            }
            synchronized(LOCK){
                INSTANCE?.let {
                    return it
                }
                val db = Room.databaseBuilder(
                    application,
                    AppDataBase::class.java,
                    DB_MAME
                ).build()
                INSTANCE = db
                return db
            }
        }
    }

}
