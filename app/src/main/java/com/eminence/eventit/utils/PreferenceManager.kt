package com.eminence.eventit.utils

import android.content.Context
import android.content.SharedPreferences

class PreferenceManager(var _context: Context) {
    var pref: SharedPreferences
    var editor: SharedPreferences.Editor

    // shared pref mode
    var PRIVATE_MODE = 0

    var isFirstTimeLaunch: Boolean
        get() = pref.getBoolean(IS_FIRST_TIME_LAUNCH, true)
        set(isFirstTime) {
            editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime)
            editor.commit()
        }

    var IsLogin: Boolean
        get() = pref.getBoolean(IS_LOGIN, false)
        set(isLogin) {
            editor.putBoolean(IS_LOGIN, isLogin)
            editor.commit()
        }


    companion object {
        // Shared preferences file name
        private const val PREF_NAME = "androidhive-welcome"
        private const val IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch"
        private const val IS_LOGIN = "IsLogin"

    }

    init {
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        editor = pref.edit()
    }
}