package com.github.linkav20.streaky.ui.userprofile.adapter

import android.content.Context
import android.view.Window
import androidx.recyclerview.widget.DiffUtil
import com.github.linkav20.streaky.ui.mytaskslist.MyTasksListFragment
import com.github.linkav20.streaky.ui.mytaskslist.adapter.MyTasksDelegates
import com.github.linkav20.streaky.ui.mytaskslist.models.MyTaskUI
import com.github.linkav20.streaky.ui.userprofile.model.NotidicationModel
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