package com.example.dt4ce.repository

import com.example.dt4ce.network.UserApi


class UserRepository(
    private val api : UserApi
) : BaseRepository(){
    suspend fun getUser() = safeApiCall {
        api.getUser()
    }

}