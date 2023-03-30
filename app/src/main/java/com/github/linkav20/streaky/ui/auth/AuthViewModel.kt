package com.github.linkav20.streaky.ui.auth

import android.content.Context
import android.content.SharedPreferences
import android.util.DisplayMetrics
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.linkav20.network.api.Api
import com.github.linkav20.streaky.fake_network.FakeApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import javax.inject.Inject

class AuthViewModel @Inject constructor(
    private val context: Context,
    private val api: Api
): ViewModel() {

    private val TAG = "ExceptionLoginViewModel"

    suspend fun login(login: String?, password: String?): Boolean {
        if (!checkUserInfo(login, password)) return false

        val isExist = viewModelScope.async(Dispatchers.IO) {
            //api.isExist(login!!, password!!)
            FakeApi.isExist(login!!, password!!)
        }.await()

        if (isExist) {
            return try {
                saveUserInfo(login!!, password!!)
                true
            } catch (ex: Exception) {
                Log.e(TAG, ex.message.toString())
                false
            }
        }

        return false
    }

    fun checkEmail(email: String?) : Boolean {
        // network request
        return email != null
    }

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

    private fun checkUserInfo(login: String?, password: String?) =
        login != null && password != null

    private suspend fun saveUserInfo(login: String, password: String) {
        val editor = getSharedPreferencesEditor() ?: throw Exception("Cannot save user info")
        saveStringInSharedPreferences(editor,
            com.github.linkav20.streaky.utils.SharedPreferences.USER_PREFERENCES_LOGIN, login)
        saveStringInSharedPreferences(editor,
            com.github.linkav20.streaky.utils.SharedPreferences.USER_PREFERENCES_PASSWORD, password)
        editor.apply()
    }

    private suspend fun getSharedPreferencesEditor() = viewModelScope.async(Dispatchers.IO) {
        context.getSharedPreferences(
            com.github.linkav20.streaky.utils.SharedPreferences.USER_PREFERENCES,
            Context.MODE_PRIVATE
        )?.edit()
    }.await()

    private suspend fun saveStringInSharedPreferences(
        preferences: SharedPreferences.Editor,
        tag: String, value: String
    ) = viewModelScope.async(Dispatchers.IO) { preferences.putString(tag, value) }.await()

}