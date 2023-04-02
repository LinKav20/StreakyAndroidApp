package com.github.linkav20.streaky.ui.creationtask

import android.content.Context
import android.graphics.Outline
import android.view.View
import android.view.ViewOutlineProvider
import android.view.Window
import com.github.linkav20.streaky.databinding.FragmentCreationTaskBinding
import com.github.linkav20.streaky.databinding.FragmentFriendsTasksListBinding
import com.github.linkav20.streaky.databinding.FragmentMainBinding
import com.github.linkav20.streaky.databinding.FragmentTasksListBinding
import eightbitlab.com.blurview.BlurView
import eightbitlab.com.blurview.RenderScriptBlur

fun blurEffectInCreationFragment(
    blurView : BlurView,
    fragment: FragmentCreationTaskBinding,
    context: Context,
    window: Window
) {
    blurView.setupWith(fragment.root, RenderScriptBlur(context)) // or RenderEffectBlu
        .setFrameClearDrawable(window.decorView.background)
        .setBlurRadius(25f)
        .setBlurAutoUpdate(true)
    blurView.outlineProvider = object : ViewOutlineProvider() {
        override fun getOutline(view: View?, outline: Outline) {
            blurView.background.getOutline(outline)
            outline.alpha = 1f
        }
    }
    blurView.clipToOutline = true
    blurView.elevation = 10F
}

fun blurEffectInMyTasksFragment(
    blurView : BlurView,
    fragment: FragmentTasksListBinding,
    context: Context,
    window: Window
) {
    blurView.setupWith(fragment.root, RenderScriptBlur(context)) // or RenderEffectBlu
        .setFrameClearDrawable(window.decorView.background)
        .setBlurRadius(25f)
        .setBlurAutoUpdate(true)
    blurView.outlineProvider = object : ViewOutlineProvider() {
        override fun getOutline(view: View?, outline: Outline) {
            blurView.background.getOutline(outline)
            outline.alpha = 1f
        }
    }
    blurView.clipToOutline = true
    blurView.elevation = 10F
}

fun blurEffectInMyFriendTasksFragment(
    blurView : BlurView,
    fragment: FragmentFriendsTasksListBinding,
    context: Context,
    window: Window
) {
    blurView.setupWith(fragment.root, RenderScriptBlur(context)) // or RenderEffectBlu
        .setFrameClearDrawable(window.decorView.background)
        .setBlurRadius(25f)
        .setBlurAutoUpdate(true)
    blurView.outlineProvider = object : ViewOutlineProvider() {
        override fun getOutline(view: View?, outline: Outline) {
            blurView.background.getOutline(outline)
            outline.alpha = 1f
        }
    }
    blurView.clipToOutline = true
    blurView.elevation = 10F
}


