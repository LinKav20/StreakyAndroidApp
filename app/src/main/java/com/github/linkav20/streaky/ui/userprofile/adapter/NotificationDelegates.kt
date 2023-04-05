package com.github.linkav20.streaky.ui.userprofile.adapter

import android.content.Context
import android.view.Window
import com.github.linkav20.streaky.databinding.MyTaskItemBinding
import com.github.linkav20.streaky.databinding.MyTaskItemLoadersBinding
import com.github.linkav20.streaky.databinding.NotificationLoaderBinding
import com.github.linkav20.streaky.databinding.NotificationRecyclerBinding
import com.github.linkav20.streaky.ui.creationtask.blurEffectInMyTasksFragment
import com.github.linkav20.streaky.ui.mytaskslist.MyTasksListFragment
import com.github.linkav20.streaky.ui.mytaskslist.models.MyTaskUI
import com.github.linkav20.streaky.ui.mytaskslist.models.MyTaskUILoading
import com.github.linkav20.streaky.ui.mytaskslist.models.MyTaskUIModel
import com.github.linkav20.streaky.ui.userprofile.model.NotidicationModel
import com.github.linkav20.streaky.ui.userprofile.model.Notification
import com.github.linkav20.streaky.ui.userprofile.model.NotificationInProgress
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

object NotificationDelegates {
    fun notificationBaseDelegate() = adapterDelegateViewBinding<NotidicationModel, Notification, NotificationRecyclerBinding>(
        { inflater, container -> NotificationRecyclerBinding.inflate(inflater, container, false) }
    ) {
        bind {
            binding.textview.text = item.text
        }
    }


    fun notificationLoadingDelegate() =
        adapterDelegateViewBinding<NotificationInProgress, Notification, NotificationLoaderBinding>(
            { inflater, container -> NotificationLoaderBinding.inflate(inflater, container, false) }
        ) {
            bind {
                binding.shimmerLayout.startShimmer()
            }
        }
}