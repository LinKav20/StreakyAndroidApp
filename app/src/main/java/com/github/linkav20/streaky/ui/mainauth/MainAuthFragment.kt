package com.github.linkav20.streaky.ui.mainauth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.linkav20.streaky.R
import com.github.linkav20.streaky.databinding.FragmentMainAuthBinding
import com.github.linkav20.streaky.ui.base.BaseFragment

class MainAuthFragment : BaseFragment() {
    private lateinit var binding: FragmentMainAuthBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainAuthBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
}