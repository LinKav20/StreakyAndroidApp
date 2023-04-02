package com.github.linkav20.streaky.ui.myfriendtaskslist.adapter

import android.content.Context
import android.view.Window
import androidx.recyclerview.widget.DiffUtil
import com.github.linkav20.streaky.ui.myfriendtaskslist.MyFriendTasksListFragment
import com.github.linkav20.streaky.ui.myfriendtaskslist.models.FriendTaskUI
import com.github.linkav20.streaky.ui.mytaskslist.models.MyTaskUI
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class FriendTaskAdapter(
    val fragment: MyFriendTasksListFragment,
    val context: Context,
    val window: Window
) : AsyncListDifferDelegationAdapter<FriendTaskUI>(DIFF_CALLBACK) {

    init {
        delegatesManager.addDelegate(FriendTaskDelegates.friendTaskItemDelegate(fragment, context, window))
            .addDelegate(FriendTaskDelegates.friendTaskLoadersDelegate(fragment, context, window))
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<FriendTaskUI>() {
            override fun areItemsTheSame(oldItem: FriendTaskUI, newItem: FriendTaskUI): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: FriendTaskUI,
                newItem: FriendTaskUI
            ): Boolean =
                oldItem.equals(newItem)
        }
    }
}