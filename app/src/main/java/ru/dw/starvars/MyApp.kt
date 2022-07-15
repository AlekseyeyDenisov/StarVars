package ru.dw.starvars

import android.app.Application
import android.content.Context
import ru.dw.starvars.data.repositories.RepositoryIpl
import ru.dw.starvars.data.network.RetrofitApiStarWars
import ru.dw.starvars.data.database.AppDataBase
import ru.dw.starvars.data.database.HelperRoomPeople


class MyApp : Application() {

    companion object {
        var appContext:Context? = null
        private var _dbRoom:AppDataBase? = null
        val dbRoom get() = _dbRoom!!
        lateinit var repository:RepositoryIpl
    }

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        initDBRoom(this)
        initRepository()

    }

    private fun initRepository() {
        repository = RepositoryIpl(RetrofitApiStarWars(), HelperRoomPeople())
    }

    private fun initDBRoom(application: Application) {
        _dbRoom = AppDataBase.getInstance(application)
    }


}


