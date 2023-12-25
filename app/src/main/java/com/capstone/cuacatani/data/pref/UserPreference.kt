package com.capstone.cuacatani.data.pref

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.capstone.cuacatani.response.LoginResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserPreference (context: Context) {

    private val preferences = context.getSharedPreferences(REFS_NAME, Context.MODE_PRIVATE)

    fun putUser(loginResult: LoginResult){
        val editor = preferences.edit()
        editor.putString(NAME, loginResult.name)
        editor.putString(EMAIL, loginResult.email)
        editor.apply()
    }

    fun gainUser(): LoginResult?{
        val gainName = preferences.getString(NAME, "")
        val gainEmail = preferences.getString(EMAIL, "")
        return LoginResult(gainName, gainEmail)
    }

    companion object{
        private const val REFS_NAME = "user_preference"
        private const val NAME = "name"
        private const val EMAIL = "email"
    }
}