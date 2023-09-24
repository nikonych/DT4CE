package com.example.dt4ce.network

import androidx.lifecycle.LiveData
import com.example.dt4ce.network.model.Content
import com.example.dt4ce.network.model.InitiativesList
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface InitiativesApi {

    @GET("initiatives/all")
    suspend fun getAllInitiatives(
        @Query("page")
        page: Int,
        @Query("size")
        size: Int
    ): InitiativesList

    @GET("initiatives/{initiative_id}")
    suspend fun getInitiative(
        @Path("initiative_id")
        initiative_id: Int
    ): Content

    @GET("initiatives/{initiative_id}/generate-qr-code")
    suspend fun getQR(
        @Path("initiative_id")
        initiative_id: Int
    ): String
}