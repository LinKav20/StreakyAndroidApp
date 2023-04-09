package com.github.linkav20.network.models.loginform

import com.google.gson.annotations.SerializedName

data class UserLoginFormResponse(
    @SerializedName("token")
    val token: String,
    @SerializedName("user")
    val user: String,
    @SerializedName("id")
    val id: Int
)
