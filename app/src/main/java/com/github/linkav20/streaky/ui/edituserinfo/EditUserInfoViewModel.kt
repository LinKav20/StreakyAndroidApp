package com.github.linkav20.streaky.ui.edituserinfo

import android.content.Context
import androidx.lifecycle.ViewModel
import com.github.linkav20.network.api.Api
import javax.inject.Inject

class EditUserInfoViewModel @Inject constructor(
    private val context: Context,
    private val api: Api
) : ViewModel() {
}