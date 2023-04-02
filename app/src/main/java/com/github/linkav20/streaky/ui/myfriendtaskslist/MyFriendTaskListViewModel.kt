package com.github.linkav20.streaky.ui.myfriendtaskslist

import android.content.Context
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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class MyFriendTaskListViewModel @Inject constructor(
    val context: Context,
    val api: Api
)  : ViewModel() {

    private val _data = MutableLiveData<List<FriendTaskUI>>()
    val data: LiveData<List<FriendTaskUI>> = _data

    init {
        initData()
    }

    private fun getFakeData() = listOf(
        FriendTaskUIModel(0, null, "First", false, false),
        FriendTaskUIModel(0, null, "Second", false, false),
        FriendTaskUIModel(0, null, "Third", false, false),
        FriendTaskUIModel(0, null, "Chetvertiy", false, false),
        FriendTaskUIModel(0, null, "5", false, false),
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
}