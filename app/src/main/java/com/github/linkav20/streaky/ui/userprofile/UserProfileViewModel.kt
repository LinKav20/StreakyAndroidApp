package com.github.linkav20.streaky.ui.userprofile

import android.content.Context
import androidx.lifecycle.ViewModel
import com.github.linkav20.network.api.Api
import javax.inject.Inject

class UserProfileViewModel @Inject constructor(
    val context: Context,
    val api: Api
) : ViewModel() {
}