package com.github.linkav20.streaky.ui.appactivity

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.linkav20.network.api.Api
import com.github.linkav20.streaky.fake_network.FakeApi
import kotlinx.coroutines.Dispatchers
import com.github.linkav20.streaky.utils.SharedPreferences.USER_PREFERENCES as USER_PREFERENCES
import com.github.linkav20.streaky.utils.SharedPreferences.USER_PREFERENCES_LOGIN as USER_PREFERENCES_LOGIN
import com.github.linkav20.streaky.utils.SharedPreferences.USER_PREFERENCES_PASSWORD as USER_PREFERENCES_PASSWORD
import kotlinx.coroutines.async
import javax.inject.Inject

class AppActivityViewModel @Inject constructor(
    private val context: Context,
    private val api: Api
) : ViewModel() {

    suspend fun isAuth(): Boolean {
        val preferences = getPreferences()
        val login = getUserLogin(preferences)
        val password = getUserPassword(preferences)

        if (login == null || password == null) return false

        return auth(login, password)
    }

    private suspend fun auth(login: String, password: String): Boolean =
        viewModelScope.async(Dispatchers.IO) {
            //api.isAuth(login, password)
            FakeApi.isAuth(login, password)
        }.await()

    private suspend fun getPreferences() = viewModelScope.async(Dispatchers.IO) {
        context.getSharedPreferences(USER_PREFERENCES, Context.MODE_PRIVATE)
    }.await()

    private suspend fun getUserLogin(preferences: SharedPreferences) =
        viewModelScope.async(Dispatchers.IO) {
            preferences.getString(USER_PREFERENCES_LOGIN, null)
        }.await()

    private suspend fun getUserPassword(preferences: SharedPreferences) = viewModelScope.async(
        Dispatchers.IO
    ) {
        preferences.getString(USER_PREFERENCES_PASSWORD, null)
    }.await()

}
