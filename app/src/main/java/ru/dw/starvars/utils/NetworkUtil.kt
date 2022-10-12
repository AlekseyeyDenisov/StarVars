package ru.dw.starvars.utils

import android.content.Context
import android.net.ConnectivityManager
import android.widget.Toast
import ru.dw.starvars.R


object NetworkUtil {
    fun getConnectivityStatusString(context: Context): Boolean {
        val cm = context
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val result = (null != cm.activeNetwork)
        if (!result) Toast.makeText(
            context,
            context.getString(R.string.no_internet_connection),
            Toast.LENGTH_SHORT
        ).show()
        return result

    }


}