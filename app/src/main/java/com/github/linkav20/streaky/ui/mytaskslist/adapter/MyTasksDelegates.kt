package com.github.linkav20.streaky.ui.mytaskslist.adapter

import android.content.Context
import android.view.View
import android.view.Window
import androidx.core.content.ContextCompat
import com.github.linkav20.streaky.R
import com.github.linkav20.streaky.databinding.MyTaskItemBinding
import com.github.linkav20.streaky.databinding.MyTaskItemLoadersBinding
import com.github.linkav20.streaky.ui.creationtask.blurEffectInMyTasksFragment
import com.github.linkav20.streaky.ui.mytaskslist.MyTasksListFragment
import com.github.linkav20.streaky.ui.mytaskslist.models.MyTaskUI
import com.github.linkav20.streaky.ui.mytaskslist.models.MyTaskUILoading
import com.github.linkav20.streaky.ui.mytaskslist.models.MyTaskUIModel
import com.github.linkav20.streaky.ui.mytaskslist.models.TaskState
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

object MyTasksDelegates {
    fun myTaskItemDelegate(
        fragment: MyTasksListFragment,
        context: Context,
        window: Window
    ) = adapterDelegateViewBinding<MyTaskUIModel, MyTaskUI, MyTaskItemBinding>(
        { inflater, container -> MyTaskItemBinding.inflate(inflater, container, false) }
    ) {
        bind {
            binding.titleTextview.text = item.title
            binding.previousDayCheckbox.isChecked = item.isPrevDone
            binding.todayCheckbox.isChecked = item.isTodayDone

            binding.previousDayCheckbox.setOnCheckedChangeListener { _, isChecked ->
                fragment.checkBoxClicked(item.copy(isPrevDone = isChecked))
            }

            binding.todayCheckbox.setOnCheckedChangeListener { _, isChecked ->
                fragment.checkBoxClicked(item.copy(isTodayDone = isChecked))
            }

            binding.mainLayout.setOnClickListener {
                fragment.onItemClicked(item.id)
            }

            if (item.taskState == TaskState.DONE) {
                binding.selectorLayout.visibility = View.GONE
                binding.statusTextview.visibility = View.VISIBLE
                binding.statusTextview.text = context.resources.getString(R.string.done)
                binding.statusTextview.setTextColor(
                    ContextCompat.getColor(
                        context,
                        R.color.lavender
                    )
                )
            }


            if (item.taskState == TaskState.FAILED) {
                binding.selectorLayout.visibility = View.GONE
                binding.statusTextview.visibility = View.VISIBLE
                binding.statusTextview.text = context.resources.getString(R.string.failed)
                binding.statusTextview.setTextColor(ContextCompat.getColor(context, R.color.orange))
            }

            blurEffectInMyTasksFragment(binding.dayBlurview, fragment.binding, context, window)
        }
    }


    fun myTaskLoadersDelegate(
        fragment: MyTasksListFragment,
        context: Context,
        window: Window
    ) =
        adapterDelegateViewBinding<MyTaskUILoading, MyTaskUI, MyTaskItemLoadersBinding>(
            { inflater, container -> MyTaskItemLoadersBinding.inflate(inflater, container, false) }
        ) {
            bind {
                binding.myTaskShimmerLayout.startShimmer()
                blurEffectInMyTasksFragment(binding.dayBlurview, fragment.binding, context, window)
            }
        }
}