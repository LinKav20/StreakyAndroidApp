package com.github.linkav20.streaky.ui.edituserinfo

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.viewModelScope
import com.github.linkav20.network.api.Api
import com.github.linkav20.streaky.ui.base.BaseViewModel
import com.github.linkav20.streaky.ui.edituserinfo.model.UserUIModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import javax.inject.Inject
import com.github.linkav20.streaky.utils.SharedPreferences.USER_PREFERENCES as USER_PREFERENCES
import com.github.linkav20.streaky.utils.SharedPreferences.USER_PREFERENCES_LOGIN as USER_PREFERENCES_LOGIN
import com.github.linkav20.streaky.utils.SharedPreferences.USER_PREFERENCES_PASSWORD as USER_PREFERENCES_PASSWORD

class EditUserInfoViewModel @Inject constructor(
    private val context: Context,
    private val api: Api
) : BaseViewModel() {

    suspend fun logout() {
        // api.logout

        val editor = getSharedPreferencesEditor()
        if (editor != null) {
            putNullForString(editor, USER_PREFERENCES_LOGIN)
            putNullForString(editor, USER_PREFERENCES_PASSWORD)
            editor?.apply()
        }
    }

    suspend fun getUserInfo(): UserUIModel {
        // api get user
        return UserUIModel("Flinkou", "flinkou@mail.ru", null)
    }

    private suspend fun getSharedPreferencesEditor() = viewModelScope.async(Dispatchers.IO) {
        context.getSharedPreferences(USER_PREFERENCES, Context.MODE_PRIVATE)?.edit()
    }.await()

    private suspend fun putNullForString(
        preferences: SharedPreferences.Editor,
        tag: String
    ) = viewModelScope.async(Dispatchers.IO) { preferences.putString(tag, null) }.await()
}