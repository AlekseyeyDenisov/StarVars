package ru.dw.starvars.utils

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.widget.Toast
import ru.dw.starvars.R
import javax.inject.Inject
import javax.inject.Singleton


class NetworkUtil @Inject constructor(private val application: Application) {
    fun getConnectivityStatusString(): Boolean {
        val cm = application
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val result = (null != cm.activeNetwork)
        if (!result) Toast.makeText(
            application.applicationContext,
            application.getString(R.string.no_internet_connection),
            Toast.LENGTH_SHORT
        ).show()
        return result

    }
}

