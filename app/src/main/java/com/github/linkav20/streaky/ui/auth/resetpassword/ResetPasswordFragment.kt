package com.github.linkav20.streaky.ui.auth.resetpassword

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.github.linkav20.streaky.R
import com.github.linkav20.streaky.databinding.FragmentResetPasswordBinding
import com.github.linkav20.streaky.ui.auth.AuthComponent
import com.github.linkav20.streaky.ui.auth.AuthViewModel
import com.github.linkav20.streaky.ui.base.BaseFragment
import kotlinx.coroutines.launch

class ResetPasswordFragment : BaseFragment() {

    private val component by lazy { AuthComponent.create() }

    private val viewModel by viewModels<AuthViewModel> { component.viewModelFactory() }

    private lateinit var binding: FragmentResetPasswordBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentResetPasswordBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.resetPasswordButton.setOnClickListener {
            lifecycleScope.launch { onResetPasswordButtonClicked() }
        }
    }

    private suspend fun onResetPasswordButtonClicked() {
        val email = binding.emailEdittext.text.toString()

        if (viewModel.checkEmail(email))
            gotoChangePassword()
        else {
            viewModel.snackBar(
                binding.root,
                resources.getString(R.string.failed_try_again),
                resources
            )
        }
    }

    private fun gotoChangePassword() {
        binding.resetPasswordButton.setOnClickListener {
            findNavController().navigate(R.id.action_resetPasswordFragment_to_changePasswordFragment)
        }
    }
}