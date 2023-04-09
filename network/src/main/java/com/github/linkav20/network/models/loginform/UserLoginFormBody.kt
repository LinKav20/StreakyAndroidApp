package com.github.linkav20.network.models.loginform

import com.google.gson.annotations.SerializedName

data class UserLoginFormBody(
    @SerializedName("email")
    val login: String,

    @SerializedName("password")
    val password: String
)