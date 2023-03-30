package com.github.linkav20.streaky.ui.signup

import android.content.Context
import android.util.DisplayMetrics
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.linkav20.network.api.Api
import com.github.linkav20.streaky.fake_network.FakeApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import javax.inject.Inject

class SignupViewModel @Inject constructor(
    val context: Context,
    val api: Api
) : ViewModel() {

    suspend fun signup(
        login: String?,
        email: String?,
        password: String?,
        repeatedPassword: String?
    ): Boolean {
        if (!checkUserInfo(login, email, password, repeatedPassword)) return false

        val isCreate = viewModelScope.async(Dispatchers.IO) {
            //api.signUp(login!!, password!!)
            FakeApi.signUp(login!!, email!!, password!!)
        }.await()

        // TODO handling error

        return isCreate
    }

    fun convertDpToPixel(dp: Float) =
        -(dp * (context.resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT))

    private fun checkUserInfo(
        login: String?,
        email: String?,
        password: String?,
        repeatedPassword: String?
    ) : Boolean {
        val first = login != null && password != null && email != null && repeatedPassword != null
        val sec = password == repeatedPassword
        return first && sec
    }
}