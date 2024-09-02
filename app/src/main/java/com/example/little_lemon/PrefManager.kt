package com.example.little_lemon

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences

class PrefManager(val context: Context) {
    private val sharedPref: SharedPreferences = context.getSharedPreferences(
        "user_data", Context.MODE_PRIVATE
    )
    private var registerState: String = "user_registered"

    object KEYS {
        const val NAME = "name"
        const val SURNAME = "surname"
        const val EMAIL = "email"
    }

    @SuppressLint("CommitPrefEdits")
    fun saveData(map: MutableMap<String, String>) {
        val editor = sharedPref.edit()
        map.forEach { (key, value) -> editor.putString(key, value) }
        editor.putBoolean(registerState, true)
        editor.apply()
    }

    fun isDataRegistered(): Boolean {
        return sharedPref.getBoolean(
            registerState, false
        )
    }

    fun getData(key: String): String {
        return sharedPref.getString(key, "").toString()
    }

    fun clearData() {
        sharedPref.edit().clear().apply()
    }
}