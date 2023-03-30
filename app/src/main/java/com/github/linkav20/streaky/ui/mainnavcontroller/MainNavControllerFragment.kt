package com.github.linkav20.streaky.ui.mainnavcontroller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.github.linkav20.streaky.R
import com.github.linkav20.streaky.databinding.FragmentMainBottonNavBinding
import com.github.linkav20.streaky.databinding.FragmentMainNavControllerBinding
import com.github.linkav20.streaky.ui.base.BaseFragment

class MainNavControllerFragment : BaseFragment() {

    private lateinit var binding: FragmentMainNavControllerBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainNavControllerBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       /* val navFragment = activity?.supportFragmentManager?.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navFragment.findNavController()
        setupActionBarWithNavController(navController)*/
    }

}