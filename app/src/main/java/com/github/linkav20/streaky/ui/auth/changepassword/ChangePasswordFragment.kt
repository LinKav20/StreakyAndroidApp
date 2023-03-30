package com.github.linkav20.streaky.ui.auth.changepassword

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.github.linkav20.streaky.R
import com.github.linkav20.streaky.databinding.FragmentChangePasswordBinding
import com.github.linkav20.streaky.ui.auth.AuthComponent
import com.github.linkav20.streaky.ui.auth.AuthViewModel
import com.github.linkav20.streaky.ui.base.BaseFragment
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class ChangePasswordFragment : BaseFragment() {

    private val component by lazy { AuthComponent.create() }

    private val viewModel by viewModels<AuthViewModel> { component.viewModelFactory() }

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

        binding.changePasswordButton.setOnClickListener {
            lifecycleScope.launch { onChangePasswordButtonClicked() }
        }
    }

    private suspend fun onChangePasswordButtonClicked() {
        val password = binding.passwordEdittext.text.toString()
        val passwordRepeat = binding.repeatPasswordEdittext.text.toString()

        if (viewModel.checkPassword(password, passwordRepeat))
            gotoLogin()
        else {
            viewModel.snackBar(
                binding.root,
                resources.getString(R.string.failed_try_again)
            )
        }
    }

    private fun gotoLogin(){
        findNavController().navigate(R.id.action_changePasswordFragment_to_loginFragment)
    }

}