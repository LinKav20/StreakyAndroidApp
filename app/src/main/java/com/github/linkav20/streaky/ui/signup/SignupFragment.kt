package com.github.linkav20.streaky.ui.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.github.linkav20.streaky.R
import com.github.linkav20.streaky.databinding.FragmentSignupBinding
import com.github.linkav20.streaky.ui.base.BaseFragment
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch


class SignupFragment : BaseFragment() {

    private val component by lazy { SignupComponent.create() }

    private val viewModel by viewModels<SignupViewModel> { component.viewModelFactory() }

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
            lifecycleScope.launch { onLoginButtonClicked() }
        }

        setDirections()
    }

    private suspend fun onLoginButtonClicked() {
        val login = binding.loginEdittext.text.toString()
        val email = binding.emailEdittext.text.toString()
        val password = binding.passwordEdittext.text.toString()
        val repeatedPassword = binding.repeatPasswordEdittext.text.toString()

        if (viewModel.signup(
                login = login,
                email = email,
                password = password,
                repeatedPassword = repeatedPassword
            )
        )
            gotoLogin()
        else {
            tmpSnackbar()
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

    private fun tmpSnackbar() {
        val snackbar: Snackbar = Snackbar.make(
            binding.root,
            resources.getString(R.string.failed_try_again),
            Snackbar.LENGTH_SHORT
        )
        val snackBarView = snackbar.view
        snackBarView.translationY = viewModel.convertDpToPixel(50f)
        snackbar.show()
    }
}