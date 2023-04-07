package com.github.linkav20.network.models

import com.google.gson.annotations.SerializedName

data class UserLoginFormRequest(
    @SerializedName("userId")
    val userId: Int
)
