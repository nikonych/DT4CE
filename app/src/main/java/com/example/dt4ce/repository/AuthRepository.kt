package com.example.dt4ce.repository

import com.example.dt4ce.network.AuthApi
import com.example.dt4ce.network.model.LoginData
import com.example.dt4ce.preferences.UserPreferences


class  AuthRepository(
    private val api : AuthApi,
    private val preferences: UserPreferences
):BaseRepository(){
    suspend fun login(
        loginData: LoginData
    )= safeApiCall { api.login(loginData) }


    suspend fun saveAuthToken(token:String)
    {
        preferences.saveAuthToken(token)
    }
}