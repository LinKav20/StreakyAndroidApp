package com.github.linkav20.streaky.ui.mytaskslist

import android.content.Context
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.linkav20.network.api.Api
import com.github.linkav20.streaky.fake_network.FakeApi
import com.github.linkav20.streaky.ui.base.BaseViewModel
import com.github.linkav20.streaky.ui.mytaskslist.models.MyTaskUI
import com.github.linkav20.streaky.ui.mytaskslist.models.MyTaskUILoading
import com.github.linkav20.streaky.ui.mytaskslist.models.MyTaskUIModel
import com.github.linkav20.streaky.ui.mytaskslist.models.TaskState
import com.github.linkav20.streaky.utils.Utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class MyTaskListViewModel @Inject constructor(
    val context: Context,
    val api: Api
) : BaseViewModel() {

    private val _data = MutableLiveData<List<MyTaskUI>>()
    val data: LiveData<List<MyTaskUI>> = _data

    init {
        initData()
    }

    fun snackBar(view: View, text: String) {
        Utils.showSnackBar(view, text, context.resources)
    }

    suspend fun updateTask(taskUIModel: MyTaskUIModel) {
        // map to server model
        //FakeApi.updateTask(taskUIModel)
        updateLocally(taskUIModel)
    }

    private fun updateLocally(taskUIModel: MyTaskUIModel) {
        val list = _data.value?.toMutableList() ?: return
        val index = list.indexOfFirst { it.id == taskUIModel.id }
        list[index] = taskUIModel
        _data.postValue(list)
    }

    private fun getFakeData() = listOf(
        MyTaskUIModel(0, "Lol", TaskState.DONE,true, false),
        MyTaskUIModel(1, "Kekekkeke", TaskState.FAILED,true, false),
        MyTaskUIModel(2, "--", TaskState.IN_PROCESS, true, true)
    )

    private fun getLoaders() = IntRange(1, 6).map { MyTaskUILoading }

    private fun initData() {
        _data.postValue(getLoaders())
        viewModelScope.launch(Dispatchers.IO) {
            FakeApi.getAllTasks()
            delay(2000)
            // TODO
            //val tasks = api.getAllTasks()
            // convert tasks to MyModel
            //_data.postValue(tasks)
            _data.postValue(getFakeData())
        }
    }

}