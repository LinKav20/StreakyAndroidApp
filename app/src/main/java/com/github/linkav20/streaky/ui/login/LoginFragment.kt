package com.github.linkav20.streaky.ui.login

import android.content.Context
import android.os.Bundle
import android.transition.TransitionInflater
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.github.linkav20.streaky.R
import com.github.linkav20.streaky.databinding.FragmentLoginBinding
import com.github.linkav20.streaky.ui.AppActivity
import com.github.linkav20.streaky.ui.base.BaseFragment
import com.github.linkav20.streaky.utils.SharedPreferences
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class LoginFragment : BaseFragment() {

    private val component by lazy { LoginComponent.create() }

    private val viewModel by viewModels<LoginViewModel> { component.viewModelFactory() }

    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loginButton.setOnClickListener {
            if (viewModel.login(
                    binding.loginEdittext.text.toString(),
                    binding.passwordEdittext.text.toString()
                )
            ) {
                val act = activity
                if (act is AppActivity) {
                    act.gotoMainFragment()
                }
            } else
                tmpSnackbar()
        }

        setDirections()
    }

    private fun setDirections() {
        binding.signInButton.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signupFragment)
        }

        binding.forgotPasswordButton.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_resetPasswordFragment)
        }
    }

    private fun tmpSnackbar() {
        Snackbar.make(binding.root, "Failed!", Snackbar.LENGTH_LONG).show()
    }
}