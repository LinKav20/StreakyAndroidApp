package com.github.linkav20.streaky

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.ViewOutlineProvider
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.github.linkav20.streaky.databinding.ActivityMainBinding
import com.github.linkav20.streaky.databinding.FragmentCreationTaskBinding
import com.github.linkav20.streaky.databinding.FragmentLoginBinding
import com.github.linkav20.streaky.databinding.FragmentSignupBinding
import eightbitlab.com.blurview.RenderScriptBlur


class MainActivity : AppCompatActivity() {
    private lateinit var binding: FragmentCreationTaskBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(
            FragmentCreationTaskBinding.inflate(layoutInflater).also { binding = it }.root
        )

        /* binding.blurView.setupWith(binding.root, RenderScriptBlur(this)) // or RenderEffectBlur
                 .setBlurRadius(15f)
                 .setBlurAutoUpdate(true)

             binding.button.setOnClickListener {
                 Toast.makeText(this,"Clicked!",Toast.LENGTH_SHORT).show()
             }*/
    }

    @SuppressLint("ResourceAsColor")
    override fun onStart() {
        super.onStart()
        fullscreen()
        val blurs = arrayOf(
            binding.fridayBlurview,
            binding.mondayBlurview,
            binding.tuesdayBlurview,
            binding.wednesdayBlurview,
            binding.thursdayBlurview,
            binding.saturdayBlurview,
            binding.sundayBlurview,
            binding.punishmentBlurview
        )

        for (blur in blurs) {
            blur.setupWith(binding.root, RenderScriptBlur(this)) // or RenderEffectBlu
                .setFrameClearDrawable(window.decorView.background)
                .setBlurRadius(25f)
                .setBlurAutoUpdate(true)
            //blur.setBackgroundColor(R.color.lavender)
            blur.outlineProvider = ViewOutlineProvider.BACKGROUND
            blur.clipToOutline = true
        }
    }

    private fun fullscreen() {
        supportActionBar?.hide()


        val windowInsetsController =
            WindowCompat.getInsetsController(window, window.decorView) ?: return
        windowInsetsController.systemBarsBehavior =
            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        windowInsetsController.hide(WindowInsetsCompat.Type.systemBars())
    }
}

