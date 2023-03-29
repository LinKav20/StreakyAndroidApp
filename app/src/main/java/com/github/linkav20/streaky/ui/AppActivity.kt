package com.github.linkav20.streaky.ui

import android.graphics.Color
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.github.linkav20.streaky.R
import com.github.linkav20.streaky.databinding.*
import com.github.linkav20.streaky.ui.main.MainFragment
import com.github.linkav20.streaky.ui.mainauth.MainAuthFragment


class AppActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ActivityMainBinding.inflate(layoutInflater).also { binding = it }.root)
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