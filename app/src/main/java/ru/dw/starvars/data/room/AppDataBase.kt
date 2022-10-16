package ru.dw.starvars.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.dw.starvars.data.room.entity.CharactersDBModel
import ru.dw.starvars.data.room.entity.ValueAttrEntity


@Database(
    entities = [
        CharactersDBModel::class,
        ValueAttrEntity::class
    ], version = 1
)
abstract class AppDataBase : RoomDatabase() {
    companion object {
        private var db: AppDataBase? = null
        private const val DB_NAME = "db_star_wars"
        private val LOCK = Any()
        fun getInstance(context: Context): AppDataBase {
            synchronized(LOCK) {
                db?.let { return it }
                val instance =
                    Room.databaseBuilder(
                        context,
                        AppDataBase::class.java,
                        DB_NAME
                    ).build()
                db = instance
                return instance
            }
        }
    }

    abstract fun chaptersDao(): CharactersDao
    abstract fun valueDao(): ValueAttrDao
}

