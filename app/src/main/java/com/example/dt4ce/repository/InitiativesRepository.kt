package com.example.dt4ce.repository

import com.example.dt4ce.network.InitiativesApi

class InitiativesRepository(
    val api: InitiativesApi
) : BaseRepository() {

    suspend fun getAllInitiatives(page: Int, size: Int) =
        safeApiCall { api.getAllInitiatives(page, size) }

    suspend fun getInitiative(id: Int) =
        safeApiCall { api.getInitiative(id) }

    suspend fun getQR(id: Int) =
        safeApiCall { api.getQR(id) }
}