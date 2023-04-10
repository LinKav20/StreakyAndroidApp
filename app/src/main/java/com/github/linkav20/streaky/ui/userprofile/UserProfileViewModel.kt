package com.github.linkav20.streaky.ui.userprofile

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.github.linkav20.network.api.Api
import com.github.linkav20.streaky.R
import com.github.linkav20.streaky.fake_network.FakeApi
import com.github.linkav20.streaky.ui.base.BaseViewModel
import com.github.linkav20.streaky.ui.userprofile.model.NotificationModel
import com.github.linkav20.streaky.ui.userprofile.model.Notification
import com.github.linkav20.streaky.ui.userprofile.model.NotificationInProgress
import com.github.linkav20.streaky.ui.userprofile.model.UserUIForm
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserProfileViewModel @Inject constructor(
    val context: Context,
    val api: Api
) : BaseViewModel() {

    private val _notification = MutableLiveData<List<Notification>>()
    val notification: LiveData<List<Notification>> = _notification

    init {
        initData()
    }

    suspend fun getUserInfo() : UserUIForm {
        return UserUIForm("Flinkou", null, 5, 10, 1)
    }

    private fun getLoaders() = IntRange(1, 6).map { NotificationInProgress }

    private fun getFakeData() = listOf(
        NotificationModel(0, context.resources.getString(R.string.notification_lorem)),
        NotificationModel(1, context.resources.getString(R.string.notification_lorem)),
        NotificationModel(2, context.resources.getString(R.string.notification_lorem)),
        NotificationModel(3, context.resources.getString(R.string.notification_lorem)),
        NotificationModel(4, context.resources.getString(R.string.notification_lorem)),
        NotificationModel(5, context.resources.getString(R.string.notification_lorem))
    )

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