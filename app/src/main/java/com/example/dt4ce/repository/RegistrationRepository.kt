package com.example.dt4ce.repository

import android.util.Log
import com.example.dt4ce.network.AuthApi
import com.example.dt4ce.network.model.RegistrationData
import okhttp3.RequestBody

class RegistrationRepository(
    private val api : AuthApi
): BaseRepository() {
    suspend fun signup(
        registrationData: RegistrationData
    )=safeApiCall { Log.d("gg", registrationData.toString())
        api.signup(registrationData) }

}