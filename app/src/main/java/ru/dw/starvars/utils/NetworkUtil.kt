package ru.dw.starvars.utils

import android.content.Context
import android.net.ConnectivityManager


object NetworkUtil {

    fun getConnectivityStatusString(context: Context): Boolean {
        val cm = context
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return (null != cm.activeNetwork)

    }


}