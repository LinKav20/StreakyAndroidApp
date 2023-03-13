package com.github.linkav20.streaky.ui.login

import android.content.Context
import androidx.lifecycle.ViewModel
import com.github.linkav20.streaky.utils.SharedPreferences
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val context: Context
) : ViewModel() {

    init {
    }

    fun login(login: String?, password: String?): Boolean {
        // send to backend

        if (login == "admin" && password == "admin") {
            val editor = context.getSharedPreferences(
                SharedPreferences.USER_PREFERENCES,
                Context.MODE_PRIVATE
            )?.edit()
            editor?.putString(SharedPreferences.USER_PREFERENCES_LOGIN, login)
            editor?.putString(SharedPreferences.USER_PREFERENCES_PASSWORD, password)
            editor?.apply()

            return true
        }

        return false
    }

}