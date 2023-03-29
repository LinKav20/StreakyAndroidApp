package com.github.linkav20.streaky.ui.mytaskslist

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.linkav20.streaky.R
import com.github.linkav20.streaky.databinding.FragmentLoginBinding
import com.github.linkav20.streaky.databinding.FragmentTasksListBinding
import com.github.linkav20.streaky.ui.base.BaseFragment

class MyTasksListFragment : BaseFragment() {

    private lateinit var binding: FragmentTasksListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTasksListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}