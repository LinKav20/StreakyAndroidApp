package com.github.linkav20.streaky.ui.creationtask

import android.content.Context
import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.linkav20.network.api.Api
import com.github.linkav20.streaky.R
import com.github.linkav20.streaky.data.Dates
import com.github.linkav20.streaky.data.UserDataHandler
import com.github.linkav20.streaky.fake_network.FakeApi
import com.github.linkav20.streaky.ui.base.BaseViewModel
import com.github.linkav20.streaky.ui.creationtask.model.CreationForm
import com.github.linkav20.streaky.ui.creationtask.model.RepeatingDayModel
import com.github.linkav20.streaky.ui.creationtask.model.User
import com.github.linkav20.streaky.utils.Utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

class CreationTaskViewModel @Inject constructor(
    private val context: Context,
    private val api: Api
) : BaseViewModel() {

    private val _creationForm = MutableLiveData<CreationForm>()
    val creationForm: LiveData<CreationForm> = _creationForm

    init {
        initCreationForm()
    }

    suspend fun create(): String {
        val creation = creationForm.value ?: return "Failed"
        val result = UserDataHandler.checkCreationForm(creation)
        if (result.isOk) {
            saveTask()
        }
        return result.message
    }

    fun updateDaysList(day: RepeatingDayModel) {
        val list =
            (_creationForm.value?.repeat ?: getDaysListAbb()).toMutableList()
        val index = list.indexOfFirst { it.name == day.name }
        list[index] = day
        _creationForm.postValue(_creationForm.value?.copy(repeat = list))
    }

    fun setNotifyInCreationForm(value: Boolean) {
        _creationForm.postValue(_creationForm.value?.copy(isNotify = value))
    }

    suspend fun getStranger() = viewModelScope.async(Dispatchers.IO) {
        User(1,"lol",  FakeApi.getRandomLogin())
    }.await()

    fun getTimeForNotifyField(): String {
        val creation = creationForm.value
        if (creation == null || creation.notifyTime == null) {
            val date = Dates.getCurrentTime()
            setTimeInCreationForm(date)
            return Dates.convertTimeToString(date)
        }
        return Dates.convertTimeToString(creation.notifyTime)
    }

    fun setTitleInCreationForm(value: String?) {
        if (UserDataHandler.checkIfNotEmpty(value))
            _creationForm.postValue(_creationForm.value?.copy(title = value!!))
    }

    fun setPunishmentInCreationForm(value: String?) {
        if (UserDataHandler.checkIfNotEmpty(value))
            _creationForm.postValue(_creationForm.value?.copy(punishment = value!!))
    }

    fun snackBar(view: View, text: String) {
        Utils.showSnackBar(view, text, context.resources)
    }

    fun getTimeFromHourAndMinuteAsString(hour: Int, minute: Int): String {
        val date = Dates.getTimeFromHourAndMinute(hour, minute)
        setTimeInCreationForm(date)
        return Dates.convertTimeToString(date)
    }

    fun getDateAsString(day: Int, month: Int, year: Int): String {
        val date = Dates.getDateFromDMY(day, month, year)
        setDeadlineInCreationForm(date)
        return Dates.convertDateToString(date)
    }

    fun getDeadlineAsString(): String {
        val date = creationForm.value?.deadline
        return if (date != null) Dates.convertDateToString(date)
        else Dates.getMonthAfterCurrentDateAsString()
    }

    suspend fun setStrangerObserver(login: String): Boolean =
        if (isExistByLogin(login)) {
            setStrangerObserverInCreationForm(login)
            true
        } else {
            setStrangerObserverInCreationForm(null)
            false
        }


    suspend fun setFriendObserver(login: String): Boolean =
        if (isExistByLogin(login)) {
            setFriendObserverInCreationForm(login)
            true
        } else {
            setFriendObserverInCreationForm(null)
            false
        }


    fun isDateMoreThanNow(year: Int, month: Int, day: Int) =
        Dates.isDateMoreThanNow(Dates.getDateFromDMY(year = year, month = month, day = day))

    fun getCalendarByDate(date: Date?): Calendar = Dates.getCalendarByDate(date)

    fun getDaysListAbb() = listOf(
        RepeatingDayModel(false, context.resources.getString(R.string.monday_abb)),
        RepeatingDayModel(false, context.resources.getString(R.string.tuesday_abb)),
        RepeatingDayModel(false, context.resources.getString(R.string.wednesday_abb)),
        RepeatingDayModel(false, context.resources.getString(R.string.thursday_abb)),
        RepeatingDayModel(false, context.resources.getString(R.string.friday_abb)),
        RepeatingDayModel(false, context.resources.getString(R.string.saturday_abb)),
        RepeatingDayModel(false, context.resources.getString(R.string.sunday_abb))
    )

    private fun setStrangerObserverInCreationForm(value: String?) {
        _creationForm.postValue(_creationForm.value?.copy(observer2 = value))
    }

    private fun setFriendObserverInCreationForm(value: String?) {
        _creationForm.postValue(_creationForm.value?.copy(observer1 = value))
    }

    private fun initCreationForm() {
        _creationForm.postValue(
            CreationForm(
                title = null,
                deadline = Dates.getMonthAfterCurrentDate(),
                repeat = getDaysListAbb(),
                isNotify = false,
                notifyTime = null,
                punishment = null,
                observer1 = null,
                observer2 = null
            )
        )
    }

    private fun setTimeInCreationForm(value: Date?) {
        _creationForm.postValue(_creationForm.value?.copy(notifyTime = value))
    }

    private fun setDeadlineInCreationForm(value: Date?) {
        _creationForm.postValue(_creationForm.value?.copy(deadline = value))
    }

    private suspend fun isExistByLogin(login: String): Boolean =
        viewModelScope.async(Dispatchers.IO) {
            delay(1000)
            FakeApi.isExist(login)
            //api.isExist(login)
        }.await()

    private suspend fun saveTask() = viewModelScope.launch(Dispatchers.IO) { }
}