package ru.dw.starvars

import android.app.Application
import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import ru.dw.starvars.data.room.DBRoom
import ru.dw.starvars.data.room.HelperRoomPeople
import ru.dw.starvars.utils.CONSTANT_TABLE_PEOPLES
import ru.dw.starvars.utils.VIEW_TAPE_CHARACTER


class MyApp : Application() {

    companion object {
        var appContext:Context? = null
        private var _dbRoom:DBRoom? = null
        val dbRoom get() = _dbRoom!!
    }

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        initDBRoom()

    }

    private fun initDBRoom() {
        _dbRoom = Room.databaseBuilder(appContext!!, DBRoom::class.java, "db_star_wars").build()
    }


}


