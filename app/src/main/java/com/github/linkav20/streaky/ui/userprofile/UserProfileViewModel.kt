package com.github.linkav20.streaky.ui.userprofile

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.linkav20.network.api.Api
import com.github.linkav20.streaky.fake_network.FakeApi
import com.github.linkav20.streaky.ui.mytaskslist.models.MyTaskUI
import com.github.linkav20.streaky.ui.mytaskslist.models.MyTaskUILoading
import com.github.linkav20.streaky.ui.userprofile.model.NotidicationModel
import com.github.linkav20.streaky.ui.userprofile.model.Notification
import com.github.linkav20.streaky.ui.userprofile.model.NotificationInProgress
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserProfileViewModel @Inject constructor(
    val context: Context,
    val api: Api
) : ViewModel() {

    private val _notification = MutableLiveData<List<Notification>>()
    val notification: LiveData<List<Notification>> = _notification

    init {
        initData()
    }

    private fun getLoaders() = IntRange(1, 6).map { NotificationInProgress }

    private fun getFakeData() = listOf(NotidicationModel(0,"gfhjdknslm;'lkfjnhbgvsbjknl;kjhvgdsbjknalmkmnjbhdvg"),
        NotidicationModel(1,"gfhjdknslm;'lkfjnhbgvsbjknl;kjhvgdsbjknalmkmnjbhdvg"),
        NotidicationModel(2,"gfhjdknslm;'lkfjnhbgvsbjknl;kjhvgdsbjknalmkmnjbhdvg"),
        NotidicationModel(3,"gfhjdknslm;'lkfjnhbgvsbjknl;kjhvgdsbjknalmkmnjbhdvg"),
        NotidicationModel(4,"gfhjdknslm;'lkfjnhbgvsbjknl;kjhvgdsbjknalmkmnjbhdvg"),
        NotidicationModel(5,"gfhjdknslm;'lkfjnhbgvsbjknl;kjhvgdsbjknalmkmnjbhdvg"))

    private fun initData() {
        _notification.postValue(getLoaders())
        viewModelScope.launch(Dispatchers.IO) {
            FakeApi.getAllTasks()
            delay(2000)
            // TODO
            //val tasks = api.getAllTasks()
            // convert tasks to MyModel
            //_data.postValue(tasks)
            _notification.postValue(getFakeData())
        }
    }

}