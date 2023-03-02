package com.github.linkav20.streaky

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.github.linkav20.streaky.databinding.LoginLayoutBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: LoginLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(LoginLayoutBinding.inflate(layoutInflater).also { binding = it }.root)
        fullscreen()


       // binding.blurView.setupWith(binding.root, RenderScriptBlur(this)) // or RenderEffectBlur
       //    .setBlurRadius(15f)
       //    .setBlurAutoUpdate(true)
    }

    private fun fullscreen(){
        supportActionBar?.hide()


        val windowInsetsController =
            WindowCompat.getInsetsController(window, window.decorView) ?: return
        windowInsetsController.systemBarsBehavior =
            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        windowInsetsController.hide(WindowInsetsCompat.Type.systemBars())
    }
}

