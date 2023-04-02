package com.github.linkav20.streaky.ui.mytaskslist.adapter

import android.content.Context
import android.view.Window
import com.github.linkav20.streaky.databinding.MyTaskItemBinding
import com.github.linkav20.streaky.databinding.MyTaskItemLoadersBinding
import com.github.linkav20.streaky.ui.creationtask.blurEffectInMyTasksFragment
import com.github.linkav20.streaky.ui.mytaskslist.MyTasksListFragment
import com.github.linkav20.streaky.ui.mytaskslist.models.MyTaskUI
import com.github.linkav20.streaky.ui.mytaskslist.models.MyTaskUILoading
import com.github.linkav20.streaky.ui.mytaskslist.models.MyTaskUIModel
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