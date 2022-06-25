package ru.dw.starvars.utils

import android.content.Context
import android.content.SharedPreferences

private const val CONSTANT_SHARED_PREFERENCES = "star_wars_sharedPreferences"
private const val SHARED_PREFERENCES_FIRST_START ="sharedPreferences_first_start"



class SharedPreferencesManager(context: Context) {
    private var pref: SharedPreferences =
        context.getSharedPreferences(CONSTANT_SHARED_PREFERENCES, Context.MODE_PRIVATE)


    fun getFirstStart(): Boolean {
        return pref.getBoolean(SHARED_PREFERENCES_FIRST_START, true)
    }

    fun setFirstStart(flag: Boolean) {
        pref.edit().putBoolean(SHARED_PREFERENCES_FIRST_START, flag).apply()
    }


}