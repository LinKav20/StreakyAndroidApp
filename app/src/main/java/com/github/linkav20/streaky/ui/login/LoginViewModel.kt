package com.github.linkav20.streaky.ui.login

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
import com.github.linkav20.streaky.utils.SharedPreferences.USER_PREFERENCES as USER_PREFERENCES
import com.github.linkav20.streaky.utils.SharedPreferences.USER_PREFERENCES_LOGIN as USER_PREFERENCES_LOGIN
import com.github.linkav20.streaky.utils.SharedPreferences.USER_PREFERENCES_PASSWORD as USER_PREFERENCES_PASSWORD


class LoginViewModel @Inject constructor(
    private val context: Context,
    private val api: Api
) : ViewModel() {

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

    fun convertDpToPixel(dp: Float) =
        -(dp * (context.resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT))

    private fun checkUserInfo(login: String?, password: String?) =
        login != null && password != null

    private suspend fun saveUserInfo(login: String, password: String) {
        val editor = getSharedPreferencesEditor() ?: throw Exception("Cannot save user info")
        saveStringInSharedPreferences(editor, USER_PREFERENCES_LOGIN, login)
        saveStringInSharedPreferences(editor, USER_PREFERENCES_PASSWORD, password)
        editor.apply()
    }

    private suspend fun getSharedPreferencesEditor() = viewModelScope.async(Dispatchers.IO) {
        context.getSharedPreferences(
            USER_PREFERENCES,
            Context.MODE_PRIVATE
        )?.edit()
    }.await()

    private suspend fun saveStringInSharedPreferences(
        preferences: SharedPreferences.Editor,
        tag: String, value: String
    ) = viewModelScope.async(Dispatchers.IO) { preferences.putString(tag, value) }.await()

}