package com.github.linkav20.streaky.ui.userprofile.adapter

import com.github.linkav20.streaky.databinding.NotificationLoaderBinding
import com.github.linkav20.streaky.databinding.NotificationRecyclerBinding
import com.github.linkav20.streaky.ui.userprofile.model.NotificationModel
import com.github.linkav20.streaky.ui.userprofile.model.Notification
import com.github.linkav20.streaky.ui.userprofile.model.NotificationInProgress
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

object NotificationDelegates {
    fun notificationBaseDelegate() = adapterDelegateViewBinding<NotificationModel, Notification, NotificationRecyclerBinding>(
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