package com.example.dt4ce.network

import com.example.dt4ce.network.model.LoginData
import com.example.dt4ce.network.model.RegistrationData
import com.example.dt4ce.responces.LoginResponse
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthApi {

    @POST("auth/login")
    suspend fun login(
        @Body loginData: LoginData
    ): LoginResponse


    @POST("auth/register")
    suspend fun signup(@Body registrationData: RegistrationData
    ):ResponseBody


}