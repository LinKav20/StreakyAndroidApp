package com.github.linkav20.streaky.ui.showmytask.model

import com.github.linkav20.streaky.ui.showmytask.TaskStatus

data class ShowDayInfoModel(
    val date: String,
    val taskStatus: TaskStatus,
    val friendStatus: String?,
    val strangerStatus: String?,
) {
}