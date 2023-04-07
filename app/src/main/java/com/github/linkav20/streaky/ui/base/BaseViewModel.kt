package com.github.linkav20.streaky.ui.base

import android.content.Context
import android.content.res.Resources
import android.view.View
import android.widget.ImageView
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.github.linkav20.streaky.utils.Utils

open class BaseViewModel : ViewModel() {
    fun snackBar(view: View, text: String, resources: Resources) {
        Utils.showSnackBar(view, text, resources)
    }

    fun setResourceImageWithGlide(
        root: View,
        image: Comparable<*>?,
        imageView: ImageView,
        radius: Int
    ) {
        Glide.with(root)
            .load(image)
            .centerCrop()
            .transform(RoundedCorners(radius))
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(imageView)
    }

    fun getImageByType(type: ImageType, context: Context): Int {
        val name = when (type) {
            ImageType.LOAD -> "rounded_corners_white"
            ImageType.ERROR -> "red"
            ImageType.SUCCESS -> "green"
        }
        return getImageByName(name, context)
    }

    fun getImageByStatus(type: TaskStatus, context: Context): Int {
        val name = when (type) {
            TaskStatus.DONE -> "ic_orange_star"
            TaskStatus.MISSED -> "ic_lavender_star"
            TaskStatus.FUTURE -> "ic_lavender_star"
            TaskStatus.NOT_NEED -> "ic_lavender_star"
        }
        return getImageByName(name, context)
    }

    fun getImageBySeen(isSeen: Boolean, context: Context): Int {
        val name = if (isSeen) "ic_orange_star" else "ic_lavender_star"
        return getImageByName(name, context)
    }

    fun getImageForAvatar(name: String, context: Context) = getImageByName(name, context)

    private fun getImageByName(name: String, context: Context) =
        context.resources.getIdentifier(
            name,
            "drawable",
            context.packageName
        )
}