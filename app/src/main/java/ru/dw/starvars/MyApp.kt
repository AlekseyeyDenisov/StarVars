package ru.dw.starvars

import android.app.Application
import androidx.room.Room
import ru.dw.starvars.data.room.DBRoom
import ru.dw.starvars.data.room.HelperRoomPeople


class MyApp : Application() {


    companion object {
        private var appContext: MyApp? = null

        private var dbRoom: DBRoom? = null

        fun getDBRoom(): HelperRoomPeople {
            if (dbRoom == null) {
                if (appContext != null) {
                    dbRoom = Room.databaseBuilder(appContext!!, DBRoom::class.java, "test").build()
                } else {
                    throw IllegalStateException("Пустой  appContext в APP")
                }
            }

            return HelperRoomPeople(dbRoom!!.peoplesDao(), dbRoom!!.attrDao())
        }
    }

    override fun onCreate() {
        super.onCreate()
        appContext = this



    }


}