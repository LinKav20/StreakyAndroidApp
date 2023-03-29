package com.github.linkav20.streaky.ui.changepassword

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.linkav20.streaky.R
import com.github.linkav20.streaky.databinding.FragmentChangePasswordBinding
import com.github.linkav20.streaky.databinding.FragmentLoginBinding
import com.github.linkav20.streaky.ui.base.BaseFragment

class ChangePasswordFragment : BaseFragment() {

    private lateinit var binding: FragmentChangePasswordBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChangePasswordBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}