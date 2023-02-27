package com.github.linkav20.streaky

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.view.ViewOutlineProvider
import com.github.linkav20.streaky.databinding.ActivityMainBinding
import eightbitlab.com.blurview.RenderScriptBlur

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ActivityMainBinding.inflate(layoutInflater).also { binding = it }.root)

        binding.blurView.setupWith(binding.root, RenderScriptBlur(this)) // or RenderEffectBlur
            .setBlurRadius(15f)
            .setBlurAutoUpdate(true)
    }
}

