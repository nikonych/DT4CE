package com.example.dt4ce.preferences

import android.content.Context
import android.content.Context.MODE_PRIVATE
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import kotlinx.coroutines.flow.flow
import java.util.concurrent.Flow

class UserPreferences(
    context: Context

){
    private val pref = context.getSharedPreferences(PREF_NAME, MODE_PRIVATE)


    val authToken: LiveData<String?> = liveData {
        emit(pref.getString(CHANGE_NAME, null))
    }


    suspend fun saveAuthToken(authToken: String) {
        pref.edit().putString(CHANGE_NAME, authToken).apply()
    }

    suspend fun clear(){
        pref.edit().clear().apply()
    }


    companion object {
        const val PREF_NAME = "pref.task"
        const val SEEN_KEY = "seen.key"
        const val CHANGE_NAME = "change.name"
        const val CHANGE_IMG = "change.img"
    }
}