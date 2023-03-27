package com.github.linkav20.streaky.ui.main

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.linkav20.streaky.R
import com.github.linkav20.streaky.databinding.FragmentMainBinding
import com.github.linkav20.streaky.utils.SharedPreferences.USER_PREFERENCES
import com.github.linkav20.streaky.utils.SharedPreferences.USER_PREFERENCES_LOGIN
import com.github.linkav20.streaky.utils.SharedPreferences.USER_PREFERENCES_PASSWORD
import com.github.linkav20.streaky.ui.creationtask.CreationTaskFragment
import com.github.linkav20.streaky.ui.myfriendtaskslist.MyFriendTasksListFragment
import com.github.linkav20.streaky.ui.mytaskslist.MyTasksListFragment
import com.github.linkav20.streaky.ui.userprofile.UserProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainFragment : Fragment(R.layout.fragment_tasks_list) {

    val preferences: SharedPreferences? by lazy {
        activity?.getSharedPreferences(
            USER_PREFERENCES,
            Context.MODE_PRIVATE
        )
    }

    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        setupBottomNavigationBar()
    }

    private fun setupBottomNavigationBar() {
        val navigation: BottomNavigationView = binding.navView
        navigation.selectedItemId = R.id.menu_tasks

        navigation.setOnItemSelectedListener { item ->
            var fragment: Fragment? = null

            when (item.itemId) {
                R.id.menu_profile -> fragment = UserProfileFragment()
                R.id.menu_create -> fragment = CreationTaskFragment()
                R.id.menu_friends -> fragment = MyFriendTasksListFragment()
                R.id.menu_tasks -> fragment = MyTasksListFragment()
            }

            if (fragment != null) {

                //if (fragment is CreationTaskFragment) navigation.visibility = View.GONE

                activity?.supportFragmentManager?.beginTransaction()
                    ?.add(R.id.main_layout, fragment)
                    //?.addToBackStack(null)
                    ?.commit()
                Log.d("MY", "goto: ${fragment})")
                true
            } else {
                false
            }
        }
    }
}