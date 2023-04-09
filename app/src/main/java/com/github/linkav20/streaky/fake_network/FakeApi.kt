package com.github.linkav20.streaky.fake_network

import com.github.linkav20.network.api.Api
import com.github.linkav20.network.models.UserLoginFormBody
import com.github.linkav20.network.models.UserLoginFormResponse
import retrofit2.Response

object FakeApi : Api {
     suspend fun isExist(login: String, password: String): Boolean =
        login == "admin" && password == "admin"


     suspend fun getUserToken(login: String, password: String): String {
        TODO("Not yet implemented")
    }

     suspend fun signUp(login: String, email: String, password: String): Boolean =
        login != "admin"

     suspend fun createTask() {
        TODO("Not yet implemented")
    }

     suspend fun getAllTasks() {

    }

     suspend fun updateTask() {
    }

     suspend fun getRandomLogin(): String {
        return "lollolololol"
    }

     suspend fun isExist(login: String): Boolean =
        login == "friend" || login == "stranger"



    override suspend fun login(body: UserLoginFormBody): Response<UserLoginFormResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun createTask(token: String) {
        TODO("Not yet implemented")
    }
}
