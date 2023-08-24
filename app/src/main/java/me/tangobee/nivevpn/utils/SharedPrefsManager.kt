package me.tangobee.nivevpn.utils

import android.content.Context
import android.content.SharedPreferences

object SharedPrefsManager {
    private const val PREFS_NAME = "GeneralData"

    fun getSharedPref(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }
}