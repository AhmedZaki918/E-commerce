package com.silkysys.umco.data.local

import android.content.SharedPreferences
import javax.inject.Inject

class UserPreferences @Inject constructor(
    private var pref: SharedPreferences,
    private val editor: SharedPreferences.Editor
) {

    fun write(key: String, value: Any) {
        editor.apply {
            if (value is String) putString(key, value)
            apply()
        }
    }

    fun read(key: String) =
        pref.getString(key, "")


    fun remove(key: String) {
        editor.apply {
            remove(key)
            apply()
        }
    }
}
