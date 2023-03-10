package com.github.linkav20.streaky.ui

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.github.linkav20.streaky.databinding.*


class MainActivity : AppCompatActivity() {
    private lateinit var binding: SplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        window.statusBarColor = Color.TRANSPARENT

        setContentView(
            SplashScreenBinding.inflate(layoutInflater).also { binding = it }.root
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
        //fullscreen()
      /* val blurs = arrayOf(
            /*binding.fridayBlurview,
            binding.mondayBlurview,
            binding.tuesdayBlurview,
            binding.wednesdayBlurview,
            binding.thursdayBlurview,
            binding.saturdayBlurview,
            binding.sundayBlurview,*/
           binding.punishmentBlurview
       //binding.blurView
        )

        for (blur in blurs) {
            blur.setupWith(binding.root, RenderScriptBlur(this)) // or RenderEffectBlu
                .setFrameClearDrawable(window.decorView.background)
                .setBlurRadius(25f)
                .setBlurAutoUpdate(true)
            //blur.setBackgroundColor(R.color.lavender)
            blur.outlineProvider = object : ViewOutlineProvider() {
                override fun getOutline(view: View?, outline: Outline) {
                    blur.background.getOutline(outline)
                    outline.alpha = 1f
                }
            }
            blur.clipToOutline = true
            blur.elevation = 10F
        }*/
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

