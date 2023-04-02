package com.github.linkav20.streaky.ui.myfriendtaskslist

import android.content.Context
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.linkav20.network.api.Api
import com.github.linkav20.streaky.fake_network.FakeApi
import com.github.linkav20.streaky.ui.myfriendtaskslist.models.FriendTaskUI
import com.github.linkav20.streaky.ui.myfriendtaskslist.models.FriendTaskUILoader
import com.github.linkav20.streaky.ui.myfriendtaskslist.models.FriendTaskUIModel
import com.github.linkav20.streaky.ui.mytaskslist.models.MyTaskUILoading
import com.github.linkav20.streaky.ui.mytaskslist.models.MyTaskUIModel
import com.github.linkav20.streaky.utils.Utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class MyFriendTaskListViewModel @Inject constructor(
    val context: Context,
    val api: Api
) : ViewModel() {

    private val _data = MutableLiveData<List<FriendTaskUI>>()
    val data: LiveData<List<FriendTaskUI>> = _data

    init {
        initData()
    }

    private fun getFakeData() = listOf(
        FriendTaskUIModel(0, null, "First", true),
        FriendTaskUIModel(1, null, "Second", true),
        FriendTaskUIModel(2, null, "Third", true),
        FriendTaskUIModel(3, null, "Chetvertiy", true)
    )

    private fun getLoaders() = IntRange(1, 6).map { FriendTaskUILoader }

    private fun initData() {
        _data.postValue(getLoaders())
        viewModelScope.launch(Dispatchers.IO) {
            //FakeApi.getAllTasks()
            delay(2000)
            // TODO
            //val tasks = api.getAllTasks()
            // convert tasks to MyModel
            //_data.postValue(tasks)
            _data.postValue(getFakeData())
        }
    }

    fun snackBar(view: View, text: String) {
        Utils.showSnackBar(view, text, context.resources)
    }

    fun isSeen(id: Int): Boolean {
        val list = _data.value?.toMutableList() ?: return true
        val isDone = (list[id] as FriendTaskUIModel).isDone
        if (isDone) {
            viewModelScope.launch { sendSeen(id) }
        }
        return isDone
    }

    fun isNotify(id: Int): Boolean {
        val list = _data.value?.toMutableList() ?: return false
        val isDone = (list[id] as FriendTaskUIModel).isDone
        if (!isDone) {
            viewModelScope.launch { notifyUser() }
        }
        return !isDone
    }

    suspend fun notifyUser() {
        // TODO notify network user
    }

    suspend fun sendSeen(id: Int) {
        // TODO seen network user
        val list = _data.value?.toMutableList() ?: return
        list.removeAt(id)
        _data.postValue(list)
    }

}