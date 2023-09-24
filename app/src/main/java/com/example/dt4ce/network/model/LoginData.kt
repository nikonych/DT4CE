package com.example.dt4ce.network.model

import com.google.gson.annotations.SerializedName

data class LoginData(
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String,
)