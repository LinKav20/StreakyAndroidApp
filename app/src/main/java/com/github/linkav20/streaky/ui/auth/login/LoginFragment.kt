package com.github.linkav20.streaky.ui.auth.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.github.linkav20.streaky.R
import com.github.linkav20.streaky.databinding.FragmentLoginBinding
import com.github.linkav20.streaky.ui.appactivity.AppActivity
import com.github.linkav20.streaky.ui.auth.AuthComponent
import com.github.linkav20.streaky.ui.auth.AuthViewModel
import com.github.linkav20.streaky.ui.base.BaseFragment
import kotlinx.coroutines.launch


class LoginFragment : BaseFragment() {

    private val component by lazy { AuthComponent.create() }

    private val viewModel by viewModels<AuthViewModel> { component.viewModelFactory() }

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
        binding.loginButton.setOnClickListener {
            lifecycleScope.launch { onLoginButtonClicked() }
        }

        setDirections()
    }

    private suspend fun onLoginButtonClicked() {
        val login = binding.loginEdittext.text.toString()
        val password = binding.passwordEdittext.text.toString()

        if (viewModel.login(login, password))
            gotoMain()
        else {
            clearFields()
            viewModel.snackBar(
                binding.root,
                resources.getString(R.string.failed_try_again),
                resources
            )
        }
    }

    private fun clearFields() {
        with(binding) {
            loginEdittext.setText("")
            loginEdittext.clearFocus()
            passwordEdittext.setText("")
            passwordEdittext.clearFocus()
        }
    }

    private fun gotoMain() {
        val act = activity
        if (act is AppActivity) {
            act.gotoMainFragment()
        }
    }

    private fun setDirections() {
        binding.signInButton.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signupFragment)
        }

        binding.forgotPasswordButton.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_resetPasswordFragment)
        }
    }

}