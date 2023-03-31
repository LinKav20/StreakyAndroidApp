package com.github.linkav20.streaky.utils

import android.content.res.Resources
import android.util.DisplayMetrics
import android.view.View
import com.google.android.material.snackbar.Snackbar

object Utils {

    fun showSnackBar(view: View, text: String, resources: Resources) {
        val snackbar: Snackbar = Snackbar.make(
            view,
            text,
            Snackbar.LENGTH_SHORT
        )
        val snackBarView = snackbar.view
        snackBarView.translationY = Utils.convertDpToPixel(50f, resources)
        snackbar.show()
    }

    private fun convertDpToPixel(dp: Float, resources: Resources) =
        -(dp * (resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT))
}