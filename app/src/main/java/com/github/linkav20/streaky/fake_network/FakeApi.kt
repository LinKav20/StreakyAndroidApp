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

}
