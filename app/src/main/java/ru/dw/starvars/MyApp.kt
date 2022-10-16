package ru.dw.starvars

import android.app.Application
import android.content.Context
import androidx.room.Room
import ru.dw.starvars.data.room.DBRoom
import ru.dw.starvars.di.DaggerApplicationComponent


class MyApp : Application() {

    val component by lazy {
        DaggerApplicationComponent.factory()
            .create(this)
    }

    companion object {
        var appContext:Context? = null
        private var _dbRoom:DBRoom? = null
        val dbRoom get() = _dbRoom!!
    }

    override fun onCreate() {
        super.onCreate()
        initDBRoom(applicationContext)

    }

    private fun initDBRoom(context: Context) {
        _dbRoom = Room.databaseBuilder(context, DBRoom::class.java, "db_star_wars").build()
    }


}


