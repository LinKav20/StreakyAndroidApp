package com.github.linkav20.streaky.ui.mytaskslist

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.github.linkav20.streaky.R
import com.github.linkav20.streaky.databinding.FragmentTasksListBinding
import com.github.linkav20.streaky.ui.base.BaseFragment
import com.github.linkav20.streaky.ui.main.MainFragmentDirections
import com.github.linkav20.streaky.ui.mytaskslist.adapter.MyTasksAdapter
import com.github.linkav20.streaky.ui.mytaskslist.models.MyTaskUIModel
import kotlinx.coroutines.launch

class MyTasksListFragment : BaseFragment(), OnItemCLickListener {

    private val component by lazy { MyTasksListComponent.create() }

    private val viewModel by viewModels<MyTaskListViewModel> { component.viewModelFactory() }

    lateinit var binding: FragmentTasksListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTasksListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setTasksAdapter()
    }

    private fun setTasksAdapter() {
        val act = activity
        if (act != null) {
            initAdapter(act)
        }
    }

    private fun initAdapter(activity: Activity) {
        val adapter = MyTasksAdapter(this, activity.applicationContext, activity.window)
        binding.tasksRecyclerview.adapter = adapter
        viewModel.data.observe(viewLifecycleOwner) {
            adapter.items = it
        }
    }

    override fun checkBoxClicked(task: MyTaskUIModel) {
        lifecycleScope.launch {
            viewModel.updateTask(task)
        }
    }

    override fun onItemClicked(taskId: Long) {
        viewModel.snackBar(binding.root, "Clicked on item with ID: $taskId")
        val action =
            MainFragmentDirections.actionMainFragmentToShowMyTaskFragment(taskId.toInt())
        findNavController().navigate(action)
    }
}