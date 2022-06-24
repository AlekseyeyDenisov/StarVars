package ru.dw.starvars

import android.app.Application
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import ru.dw.starvars.data.room.DBRoom
import ru.dw.starvars.data.room.HelperRoomPeople
import ru.dw.starvars.utils.CONSTANT_TABLE_PEOPLES
import ru.dw.starvars.utils.VIEW_TAPE_CHARACTER


class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = this


    }

    companion object {
        var appContext: MyApp? = null


        private var dbRoom: DBRoom? = null

        fun getDBRoom(): HelperRoomPeople {
            if (dbRoom == null) {
                if (appContext != null) {
                    dbRoom = Room.databaseBuilder(appContext!!, DBRoom::class.java, "test")
                        .addMigrations(migration_1_2)
                        .build()
                } else {
                    throw IllegalStateException("Пустой  appContext в APP")
                }
            }
            return HelperRoomPeople(dbRoom!!.peoplesDao(), dbRoom!!.attrDao())
        }

        private val migration_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE $CONSTANT_TABLE_PEOPLES ADD column viewTape TEXT NOT NULL DEFAULT '${VIEW_TAPE_CHARACTER}'")
            }

        }
    }

}


