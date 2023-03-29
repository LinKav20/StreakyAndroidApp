package com.github.linkav20.streaky.ui.appactivity

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.github.linkav20.streaky.R
import com.github.linkav20.streaky.databinding.*
import com.github.linkav20.streaky.ui.main.MainFragment
import com.github.linkav20.streaky.ui.mainauth.MainAuthFragment
import com.github.linkav20.streaky.ui.splashscreen.SplashScreenFragment
import com.github.linkav20.streaky.utils.SharedPreferences
import com.github.linkav20.streaky.utils.SharedPreferences.USER_PREFERENCES_LOGIN
import com.github.linkav20.streaky.utils.SharedPreferences.USER_PREFERENCES_PASSWORD
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class AppActivity : AppCompatActivity() {

    private val component by lazy { AppActivityComponent.create() }

    private val viewModel by viewModels<AppActivityViewModel> { component.viewModelFactory() }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ActivityMainBinding.inflate(layoutInflater).also { binding = it }.root)
        showSplashScreen()
        lifecycleScope.launch {
            delay(1000)
            setMainScreen()
        }
    }

    fun gotoMainFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, MainFragment()).commit()
    }

    private fun showSplashScreen(){
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, SplashScreenFragment()).commit()
    }

    private suspend fun setMainScreen() {
        if (viewModel.isAuth()) gotoMainFragment()
        else gotoAuthFragment()
    }

    private fun gotoAuthFragment() {
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, MainAuthFragment())
            .commit()
    }

    override fun onStart() {
        super.onStart()

        /*val editor = getSharedPreferences(SharedPreferences.USER_PREFERENCES, Context.MODE_PRIVATE).edit()
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