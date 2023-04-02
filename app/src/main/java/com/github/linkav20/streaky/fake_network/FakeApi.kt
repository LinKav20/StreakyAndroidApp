package com.github.linkav20.streaky.fake_network

import com.github.linkav20.network.api.Api

object FakeApi : Api {
    override suspend fun isExist(login: String, password: String): Boolean =
        login == "admin" && password == "admin"


    override suspend fun getUserToken(login: String, password: String): String {
        TODO("Not yet implemented")
    }

    override suspend fun signUp(login: String, email: String, password: String): Boolean =
        login != "admin"

    override suspend fun createTask() {
        TODO("Not yet implemented")
    }

    override suspend fun getAllTasks() {

    }

    override suspend fun updateTask() {
    }

    override suspend fun getRandomLogin(): String {
        return "lollolololol"
    }

    override suspend fun isExist(login: String): Boolean =
        login == "friend" || login == "stranger"
}
