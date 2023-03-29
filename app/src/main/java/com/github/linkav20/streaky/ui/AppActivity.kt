package com.github.linkav20.streaky.ui

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.github.linkav20.streaky.R
import com.github.linkav20.streaky.databinding.*
import com.github.linkav20.streaky.ui.main.MainFragment
import com.github.linkav20.streaky.ui.mainauth.MainAuthFragment
import com.github.linkav20.streaky.utils.SharedPreferences.USER_PREFERENCES
import com.github.linkav20.streaky.utils.SharedPreferences.USER_PREFERENCES_LOGIN
import com.github.linkav20.streaky.utils.SharedPreferences.USER_PREFERENCES_PASSWORD
import com.google.android.material.bottomnavigation.BottomNavigationView


class AppActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //fullscreen()
        setContentView(ActivityMainBinding.inflate(layoutInflater).also { binding = it }.root)
        //binding.animationView.visibility = View.VISIBLE
        supportFragmentManager.beginTransaction().add(R.id.fragment_container, MainAuthFragment())
            .commit()
    }

    public fun gotoMainFragment() {
        supportFragmentManager.beginTransaction()
           .add(R.id.fragment_container, MainFragment()).commit()
    }

    override fun onStart() {
        super.onStart()

        /*val editor = preferences.edit()
        editor.putString(USER_PREFERENCES_LOGIN, null)
        editor.putString(USER_PREFERENCES_PASSWORD, null)
        editor.apply()*/
    }

    private fun fullscreen() {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        window.statusBarColor = Color.TRANSPARENT
    }

    private fun setAuthNavigation() {
        /*val myNavHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val inflater = myNavHostFragment.navController.navInflater
        val graph = inflater.inflate(R.navigation.auth_nav_graph)
        myNavHostFragment.navController.graph = graph
        binding.navHostFragment.visibility = View.VISIBLE
        binding.animationView.visibility = View.GONE*/
    }

    private fun setMainNavigation() {
        /* val myNavHostFragment =
             supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
         val inflater = myNavHostFragment.navController.navInflater
         val graph = inflater.inflate(R.navigation.main_nav_graph)
         myNavHostFragment.navController.graph = graph
         binding.navHostFragment.visibility = View.VISIBLE
         binding.animationView.visibility = View.GONE*/
    }

    private fun getUserData() {
        /* val login = preferences.getString(USER_PREFERENCES_LOGIN, null)
         val password = preferences.getString(USER_PREFERENCES_PASSWORD, null)

         if (login != null && password != null) {
             // backend query
             setMainNavigation()
         } else {
             setAuthNavigation()
         }*/
    }
}

/* binding.blurView.setupWith(binding.root, RenderScriptBlur(this)) // or RenderEffectBlur
                 .setBlurRadius(15f)
                 .setBlurAutoUpdate(true)

             binding.button.setOnClickListener {
                 Toast.makeText(this,"Clicked!",Toast.LENGTH_SHORT).show()
             }*/
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

