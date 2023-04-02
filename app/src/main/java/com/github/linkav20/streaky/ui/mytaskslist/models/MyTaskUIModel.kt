package com.github.linkav20.streaky.ui.mytaskslist.models

data class MyTaskUIModel(
    override val id: Long,
    val title: String,
    val isPrevDone: Boolean,
    val isTodayDone: Boolean
) : MyTaskUI {
}