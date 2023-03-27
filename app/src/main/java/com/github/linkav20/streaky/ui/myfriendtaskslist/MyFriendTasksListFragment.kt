package com.github.linkav20.streaky.ui.myfriendtaskslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.linkav20.streaky.R
import com.github.linkav20.streaky.databinding.FragmentFriendsTasksListBinding
import com.github.linkav20.streaky.databinding.FragmentLoginBinding
import com.github.linkav20.streaky.databinding.FragmentTasksListBinding

class MyFriendTasksListFragment : Fragment(R.layout.fragment_tasks_list) {

    private lateinit var binding: FragmentFriendsTasksListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFriendsTasksListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}