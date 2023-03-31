package com.github.linkav20.streaky.ui.creationtask.repeatdadyadapter

import androidx.recyclerview.widget.DiffUtil
import com.github.linkav20.streaky.ui.creationtask.model.RepeatingDayModel

class DiffCallback : DiffUtil.ItemCallback<RepeatingDayModel>() {
    override fun areItemsTheSame(oldItem: RepeatingDayModel, newItem: RepeatingDayModel) =
        oldItem.name == newItem.name

    override fun areContentsTheSame(oldItem: RepeatingDayModel, newItem: RepeatingDayModel) =
        oldItem == newItem

}