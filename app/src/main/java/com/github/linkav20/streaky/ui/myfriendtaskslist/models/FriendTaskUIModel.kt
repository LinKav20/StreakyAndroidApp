package com.github.linkav20.streaky.ui.myfriendtaskslist.models

data class FriendTaskUIModel(
    override val id: Long,
    val image: String?,
    val title: String,
    val isDone: Boolean
) : FriendTaskUI