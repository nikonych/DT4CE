package com.example.dt4ce.network.model

import com.google.gson.annotations.SerializedName

data class RegistrationData(
    @SerializedName("phone_number")
    val phone_number: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("confirm_password")
    val confirm_password: String
)