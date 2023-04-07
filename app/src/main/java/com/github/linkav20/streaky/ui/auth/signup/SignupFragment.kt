package com.github.linkav20.streaky.ui.auth.signup

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.github.linkav20.streaky.R
import com.github.linkav20.streaky.databinding.FragmentSignupBinding
import com.github.linkav20.streaky.ui.auth.AuthComponent
import com.github.linkav20.streaky.ui.auth.AuthViewModel
import com.github.linkav20.streaky.ui.base.BaseFragment
import kotlinx.coroutines.launch


class SignupFragment : BaseFragment() {

    private val component by lazy { AuthComponent.create() }

    private val viewModel by viewModels<AuthViewModel> { component.viewModelFactory() }

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

        binding.signUpButton.setOnClickListener {
            lifecycleScope.launch { onSignupButtonClicked() }
        }

        setDirections()
    }

    private suspend fun onSignupButtonClicked() {
        val login = binding.loginEdittext.text.toString()
        val email = binding.emailEdittext.text.toString()
        val password = binding.passwordEdittext.text.toString()
        val repeatedPassword = binding.repeatPasswordEdittext.text.toString()

        Log.d("AUTH", "$login $email $password")

        if (viewModel.signup(
                login = login,
                email = email,
                password = password,
                repeatedPassword = repeatedPassword
            )
        )
            gotoLogin()
        else {
            viewModel.snackBar(
                binding.root,
                resources.getString(R.string.failed_try_again),
                resources
            )
        }
    }

    private fun gotoLogin() {
        findNavController().navigate(R.id.action_signupFragment_to_loginFragment)
    }

    private fun setDirections() {
        binding.loginButton.setOnClickListener {
            findNavController().navigate(R.id.action_signupFragment_to_loginFragment)
        }
    }
}