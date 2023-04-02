package com.github.linkav20.streaky.ui.mytaskslist

import com.github.linkav20.streaky.ui.mytaskslist.models.MyTaskUIModel

interface OnItemCLickListener {

    fun checkBoxClicked(task: MyTaskUIModel)

    fun onItemClicked(taskId: Long)
}