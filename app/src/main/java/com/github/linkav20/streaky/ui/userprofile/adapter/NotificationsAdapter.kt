package com.github.linkav20.streaky.ui.userprofile.adapter

import androidx.recyclerview.widget.DiffUtil
import com.github.linkav20.streaky.ui.userprofile.model.Notification
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class NotificationsAdapter() : AsyncListDifferDelegationAdapter<Notification>(DIFF_CALLBACK) {

    init {
        delegatesManager.addDelegate(NotificationDelegates.notificationBaseDelegate())
            .addDelegate(NotificationDelegates.notificationLoadingDelegate())
    }


    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Notification>() {
            override fun areItemsTheSame(oldItem: Notification, newItem: Notification): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: Notification,
                newItem: Notification
            ): Boolean =
                oldItem.equals(newItem)
        }
    }
}