package com.example.dt4ce.network

import com.example.dt4ce.responces.userdetail.UserResponse
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.POST

interface UserApi {

   @GET("user")
   suspend fun getUser(): UserResponse

   @POST("logout")
   suspend fun logout(): ResponseBody
}