package com.github.linkav20.streaky.ui.myfriendtaskslist.adapter

import android.content.Context
import android.view.Window
import com.github.linkav20.streaky.databinding.FriendTaskItemBinding
import com.github.linkav20.streaky.databinding.FriendTaskItemLoaderBinding
import com.github.linkav20.streaky.ui.creationtask.blurEffectInMyFriendTasksFragment
import com.github.linkav20.streaky.ui.base.ImageType
import com.github.linkav20.streaky.ui.myfriendtaskslist.MyFriendTasksListFragment
import com.github.linkav20.streaky.ui.myfriendtaskslist.models.FriendTaskUI
import com.github.linkav20.streaky.ui.myfriendtaskslist.models.FriendTaskUILoader
import com.github.linkav20.streaky.ui.myfriendtaskslist.models.FriendTaskUIModel
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

object FriendTaskDelegates {
    fun friendTaskItemDelegate(
        fragment: MyFriendTasksListFragment,
        context: Context,
        window: Window
    ) = adapterDelegateViewBinding<FriendTaskUIModel, FriendTaskUI, FriendTaskItemBinding>(
        { inflater, container -> FriendTaskItemBinding.inflate(inflater, container, false) }
    ) {
        bind {
            binding.titleTextview.text = item.title

            binding.todayCheckbox.isChecked = item.isDone

            val image = item.image ?: fragment.viewModel.getImageByType(ImageType.ERROR, context)
            fragment.viewModel.setResourceImageWithGlide(
                binding.root,
                image,
                binding.avatarImageView,
                10
            )

            binding.mainLayout.setOnClickListener {
                fragment.onItemClickListener(item.id)
            }

            blurEffectInMyFriendTasksFragment(
                binding.dayBlurview,
                fragment.binding,
                context,
                window
            )
        }
    }


    fun friendTaskLoadersDelegate(
        fragment: MyFriendTasksListFragment,
        context: Context,
        window: Window
    ) = adapterDelegateViewBinding<FriendTaskUILoader, FriendTaskUI, FriendTaskItemLoaderBinding>(
        { inflater, container -> FriendTaskItemLoaderBinding.inflate(inflater, container, false) }
    ) {
        bind {
            binding.friendTaskShimmerLayout.startShimmer()
            blurEffectInMyFriendTasksFragment(
                binding.dayBlurview,
                fragment.binding,
                context,
                window
            )
        }
    }
}