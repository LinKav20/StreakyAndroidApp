package com.github.linkav20.streaky.ui.userprofile

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.github.linkav20.streaky.R
import com.github.linkav20.streaky.databinding.FragmentSignupBinding
import com.github.linkav20.streaky.databinding.FragmentUserProfileBinding
import com.github.linkav20.streaky.ui.base.BaseFragment
import com.github.linkav20.streaky.ui.showmytask.ShowMyTaskComponent
import com.github.linkav20.streaky.ui.showmytask.ShowMyTaskViewModel

class UserProfileFragment : BaseFragment() {

    private val component by lazy { UserProfileComponent.create() }

    private val viewModel by viewModels<UserProfileViewModel> { component.viewModelFactory() }

    private lateinit var binding: FragmentUserProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserProfileBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}