package com.github.linkav20.network.api

import com.github.linkav20.network.models.UserLoginFormBody
import com.github.linkav20.network.models.UserLoginFormResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface Api {

    /*suspend fun isExist(login: String, password: String): Boolean

    suspend fun getUserToken(login: String, password: String): String

    suspend fun signUp(login: String, email: String, password: String): Boolean

    suspend fun isExist(login: String): Boolean

    suspend fun createTask()

    suspend fun getAllTasks()

    suspend fun updateTask()

    suspend fun getRandomLogin(): String*/

    @POST("/sign_in")
    suspend fun login(@Body body: UserLoginFormBody): Response<UserLoginFormResponse>

    @POST("/")
    suspend fun createTask(@Header("X-API-KEY") token: String)

}
