package com.github.linkav20.streaky.ui.signup

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.github.linkav20.streaky.R
import com.github.linkav20.streaky.databinding.FragmentResetPasswordBinding
import com.github.linkav20.streaky.databinding.FragmentSignupBinding
import com.github.linkav20.streaky.ui.base.BaseFragment

class SignupFragment : BaseFragment() {

    private lateinit var binding: FragmentSignupBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignupBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setDirections()
    }

    private fun setDirections() {
        binding.loginButton.setOnClickListener {
            findNavController().navigate(R.id.action_signupFragment_to_loginFragment)
        }
    }
}