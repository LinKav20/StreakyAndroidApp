package com.github.linkav20.streaky.ui.main

import android.content.Context
import android.content.SharedPreferences
import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.linkav20.streaky.R
import com.github.linkav20.streaky.databinding.FragmentMainBinding
import com.github.linkav20.streaky.ui.base.BaseFragment
import com.github.linkav20.streaky.ui.creationtask.CreationTaskFragment
import com.github.linkav20.streaky.ui.mainauth.MainAuthFragment
import com.github.linkav20.streaky.ui.myfriendtaskslist.MyFriendTasksListFragment
import com.github.linkav20.streaky.ui.mytaskslist.MyTasksListFragment
import com.github.linkav20.streaky.ui.userprofile.UserProfileFragment
import com.github.linkav20.streaky.utils.SharedPreferences.USER_PREFERENCES
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainFragment : BaseFragment() {

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
        setStartFragment(navigation)

        navigation.setOnItemSelectedListener { item ->
            val fragment: Fragment? = getFragmentByItemId(item.itemId)

            if (fragment != null) {
                move(fragment)
                true
            } else {
                false
            }
        }
    }

    private fun move(fragment: Fragment) {
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.main_fragment_container, fragment)
            //?.addToBackStack(null)
            ?.commit()
    }

    private fun getFragmentByItemId(id: Int) = when (id) {
        R.id.menu_profile -> UserProfileFragment()
        R.id.menu_create -> CreationTaskFragment()
        R.id.menu_friends -> MyFriendTasksListFragment()
        R.id.menu_tasks -> MyTasksListFragment()
        else -> null
    }

    private fun setStartFragment(navigation: BottomNavigationView) {
        navigation.selectedItemId = R.id.menu_tasks
        activity?.supportFragmentManager?.beginTransaction()
            ?.add(R.id.main_fragment_container, MyTasksListFragment())
            ?.commit()
    }
}