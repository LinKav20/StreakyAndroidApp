package com.github.linkav20.streaky.ui.showmytask

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.applandeo.materialcalendarview.CalendarDay
import com.github.linkav20.network.api.Api
import com.github.linkav20.streaky.R
import com.github.linkav20.streaky.data.Dates
import com.github.linkav20.streaky.ui.base.BaseViewModel
import com.github.linkav20.streaky.ui.base.TaskStatus
import com.github.linkav20.streaky.ui.showmytask.model.ShowDayInfoModel
import com.github.linkav20.streaky.ui.showmytask.model.TaskUIModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

class ShowMyTaskViewModel @Inject constructor(
    val context: Context,
    val api: Api
) : BaseViewModel() {

    private val _showDayData = MutableLiveData<ShowDayInfoModel>()
    val showDayData: LiveData<ShowDayInfoModel> = _showDayData

    init {
        viewModelScope.launch {
            _showDayData.postValue(getShowDayModel(Dates.getCurrentTime()))
        }
    }

    suspend fun getTaskInfo(id: Int): TaskUIModel {
        // TODO network get task
        // map task
        return TaskUIModel(
            "Do kursach",
            "11.04.2023",
            "M, Th, W, F",
            "Go out from HSE",
            getFakeDoneDates(),
            "friend",
            null,
            "stranger",
            null
        )
    }

    fun showInfoAboutDay(date: Date) {
        viewModelScope.launch {
            _showDayData.postValue(getShowDayModel(date))
        }
    }

    private fun getCalendarDateByCalendar(c: Calendar) = CalendarDay(c).apply {
        labelColor = R.color.white
        backgroundResource = R.drawable.calendar_selector
    }

    private suspend fun getShowDayModel(date: Date): ShowDayInfoModel {
        // TODO goto network
        return ShowDayInfoModel(
            Dates.convertDateToString(date),
            TaskStatus.DONE,
            false,
            true
        )
    }

    private fun getFakeDoneDates(): List<CalendarDay> {
        val calendar1 = Calendar.getInstance()
        calendar1[Calendar.DAY_OF_MONTH] = 2
        val calendar2 = Calendar.getInstance()
        calendar2[Calendar.DAY_OF_MONTH] = 3
        val calendar3 = Calendar.getInstance()
        calendar3[Calendar.DAY_OF_MONTH] = 4
        val list = listOf(
            getCalendarDateByCalendar(calendar1),
            getCalendarDateByCalendar(calendar2),
            getCalendarDateByCalendar(calendar3)
        )
        return list
    }
}