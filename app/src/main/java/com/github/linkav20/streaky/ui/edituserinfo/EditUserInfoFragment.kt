package com.github.linkav20.streaky.ui.edituserinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.github.linkav20.streaky.R
import com.github.linkav20.streaky.databinding.FragmentEditProfileBinding
import com.github.linkav20.streaky.ui.base.BaseFragment
import kotlinx.coroutines.launch

class EditUserInfoFragment: BaseFragment() {

    private val component by lazy { EditUserProfileComponent.create() }

    private val viewModel by viewModels<EditUserInfoViewModel> { component.viewModelFactory() }

    lateinit var binding: FragmentEditProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditProfileBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.backArrowButton.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.changePasswordButton.setOnClickListener {
            findNavController().navigate(R.id.action_editUserInfoFragment_to_changePasswordFragment2)
        }
        binding.logoutButton.setOnClickListener {
            lifecycleScope.launch { viewModel.logout() }
            findNavController().navigate(R.id.action_editUserInfoFragment_to_appActivity)
        }
    }
}