package com.github.linkav20.network.api

interface Api {

    suspend fun isAuth(login: String, password: String): Boolean

    suspend fun getUserToken(login: String, password: String): String
}