package com.github.linkav20.streaky.ui.resetpassword

import android.content.Context
import android.util.DisplayMetrics
import androidx.lifecycle.ViewModel
import com.github.linkav20.network.api.Api
import javax.inject.Inject

class ResetPasswordViewModel @Inject constructor(
    val context: Context,
    val api: Api
) : ViewModel() {

    fun checkEmail(email: String?) : Boolean {
        // network request
        return true
    }

    fun convertDpToPixel(dp: Float) =
        -(dp * (context.resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT))
}