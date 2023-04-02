package com.github.linkav20.streaky.ui.myfriendtaskslist

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.github.linkav20.streaky.R
import com.github.linkav20.streaky.databinding.FragmentFriendsTasksListBinding
import com.github.linkav20.streaky.databinding.FragmentLoginBinding
import com.github.linkav20.streaky.databinding.FragmentTasksListBinding
import com.github.linkav20.streaky.ui.base.BaseFragment
import com.github.linkav20.streaky.ui.myfriendtaskslist.adapter.FriendTaskAdapter
import com.github.linkav20.streaky.ui.mytaskslist.MyTaskListViewModel
import com.github.linkav20.streaky.ui.mytaskslist.MyTasksListComponent

class MyFriendTasksListFragment : BaseFragment() {

    private val component by lazy { MyFriendTasksListComponent.create() }

    private val viewModel by viewModels<MyFriendTaskListViewModel> { component.viewModelFactory() }

    lateinit var binding: FragmentFriendsTasksListBinding

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

        setAdapter()
    }

    private fun setAdapter() {
        val act = activity
        if (act != null) {
            initAdapter(act)
        }
    }

    private fun initAdapter(activity: Activity) {
        val adapter = FriendTaskAdapter(this, activity.applicationContext, activity.window)
        binding.tasksRecyclerview.adapter = adapter
        viewModel.data.observe(viewLifecycleOwner) {
            adapter.items = it
        }
    }
}