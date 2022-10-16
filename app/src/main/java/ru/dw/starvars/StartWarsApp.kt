package ru.dw.starvars

import android.app.Application
import ru.dw.starvars.di.DaggerApplicationComponent


class StartWarsApp : Application() {

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }




}


