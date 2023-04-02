package com.github.linkav20.streaky.ui.mytaskslist.adapter

import android.content.Context
import android.view.Window
import androidx.recyclerview.widget.DiffUtil
import com.github.linkav20.streaky.ui.mytaskslist.MyTasksListFragment
import com.github.linkav20.streaky.ui.mytaskslist.models.MyTaskUI
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class MyTasksAdapter(
    val fragment: MyTasksListFragment,
    val context: Context,
    val window: Window
) : AsyncListDifferDelegationAdapter<MyTaskUI>(DIFF_CALLBACK) {

    init {
        delegatesManager.addDelegate(MyTasksDelegates.myTaskItemDelegate(fragment, context, window))
            .addDelegate(MyTasksDelegates.myTaskLoadersDelegate(fragment, context, window))
    }


    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MyTaskUI>() {
            override fun areItemsTheSame(oldItem: MyTaskUI, newItem: MyTaskUI): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: MyTaskUI,
                newItem: MyTaskUI
            ): Boolean =
                oldItem.equals(newItem)
        }
    }
}