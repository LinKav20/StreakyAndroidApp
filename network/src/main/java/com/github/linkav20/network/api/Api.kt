package com.github.linkav20.network.api

interface Api {

    suspend fun isExist(login: String, password: String): Boolean

    suspend fun getUserToken(login: String, password: String): String

    suspend fun signUp(login: String, email: String, password: String): Boolean

    suspend fun isExist(login: String): Boolean

    suspend fun createTask()

    suspend fun getAllTasks()

    suspend fun updateTask()
}