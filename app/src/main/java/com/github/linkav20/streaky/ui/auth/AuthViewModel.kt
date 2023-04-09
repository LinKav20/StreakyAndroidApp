package com.github.linkav20.streaky.ui.auth

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.linkav20.network.api.Api
import com.github.linkav20.network.models.UserLoginFormBody
import com.github.linkav20.streaky.data.UserDataHandler
import com.github.linkav20.streaky.fake_network.FakeApi
import com.github.linkav20.streaky.ui.base.BaseViewModel
import com.github.linkav20.streaky.utils.Utils
import com.github.linkav20.streaky.utils.model.HttpStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import retrofit2.Response
import javax.inject.Inject
import com.github.linkav20.streaky.utils.SharedPreferences.USER_PREFERENCES as USER_PREFERENCES
import com.github.linkav20.streaky.utils.SharedPreferences.USER_PREFERENCES_LOGIN as USER_PREFERENCES_LOGIN
import com.github.linkav20.streaky.utils.SharedPreferences.USER_PREFERENCES_PASSWORD as USER_PREFERENCES_PASSWORD


class AuthViewModel @Inject constructor(
    private val context: Context,
    private val api: Api
) : BaseViewModel() {

    private val TAG = "AUTH"

    suspend fun login(login: String?, password: String?): Boolean {
        if (!UserDataHandler.checkDataFromLoginForm(login, password)) return false

        val isExist = checkIsExistInServer(login!!, password!!)

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

    suspend fun signup(
        login: String?,
        email: String?,
        password: String?,
        repeatedPassword: String?
    ): Boolean {
        if (!UserDataHandler.checkDataSignUpFrom(
                login,
                email,
                password,
                repeatedPassword
            )
        ) return false

        val isCreate = viewModelScope.async(Dispatchers.IO) {
            //api.signUp(login!!, password!!)
            FakeApi.signUp(login!!, email!!, password!!)
        }.await()

        // TODO handling error

        return isCreate
    }

    fun checkEmail(email: String?): Boolean {
        // network request
        // TODO handling error
        return UserDataHandler.checkEmailField(email)
    }

    fun checkPassword(password: String?, repeatedPassword: String?) =
        UserDataHandler.checkPasswordField(password)
                && UserDataHandler.checkIfNotEmpty(repeatedPassword)
                && password == repeatedPassword

    private suspend fun saveUserInfo(login: String, password: String) {
        val editor = getSharedPreferencesEditor() ?: throw Exception("Cannot save user info")
        saveStringInSharedPreferences(editor, USER_PREFERENCES_LOGIN, login)
        saveStringInSharedPreferences(editor, USER_PREFERENCES_PASSWORD, password)
        editor.apply()
    }

    private suspend fun getSharedPreferencesEditor() = viewModelScope.async(Dispatchers.IO) {
        context.getSharedPreferences(USER_PREFERENCES, Context.MODE_PRIVATE)?.edit()
    }.await()

    private suspend fun saveStringInSharedPreferences(
        preferences: SharedPreferences.Editor,
        tag: String, value: String
    ) = viewModelScope.async(Dispatchers.IO) { preferences.putString(tag, value) }.await()

    private suspend fun checkIsExistInServer(login: String, password: String) =
        viewModelScope.async(Dispatchers.IO) {
            val response = api.login(UserLoginFormBody(login, password))
            if (response.code() == HttpStatus.OK) {
                val body = response.body()
                if (body != null) {
                    saveToken(body.token)
                    saveUserId(body.id)
                }
            }
            FakeApi.isExist(login, password)
        }.await()
}
