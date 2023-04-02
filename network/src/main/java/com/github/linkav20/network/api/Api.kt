package com.github.linkav20.network.api

import retrofit2.http.GET
import retrofit2.http.POST

interface Api {

    suspend fun isExist(login: String, password: String): Boolean

    suspend fun getUserToken(login: String, password: String): String

    suspend fun signUp(login: String, email: String, password: String): Boolean

    suspend fun isExist(login: String): Boolean

    suspend fun createTask()

    suspend fun getAllTasks()

    suspend fun updateTask()

    suspend fun getRandomLogin(): String

}

