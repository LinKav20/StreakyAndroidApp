package com.github.linkav20.streaky.ui.creationtask

import android.content.Context
import android.content.res.Resources
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.linkav20.streaky.R
import com.github.linkav20.streaky.ui.creationtask.model.RepeatingDayModel
import javax.inject.Inject

class CreationTaskViewModel @Inject constructor(
    private val context: Context
) : ViewModel() {

    private val _daysList = MutableLiveData<List<RepeatingDayModel>>()
    val daysList: LiveData<List<RepeatingDayModel>> = _daysList

    init {
        _daysList.postValue(getDaysListAbb(context.resources))
    }

    fun updateDaysList(day: RepeatingDayModel) {
        val list = (_daysList.value ?: getDaysListAbb(context.resources)).toMutableList()
        val index = list.indexOfFirst { it.name == day.name }
        list[index] = day
        _daysList.postValue(list)
    }

    private fun getDaysListAbb(resources: Resources) = listOf(
        RepeatingDayModel(false, resources.getString(R.string.monday_abb)),
        RepeatingDayModel(false, resources.getString(R.string.tuesday_abb)),
        RepeatingDayModel(false, resources.getString(R.string.wednesday_abb)),
        RepeatingDayModel(false, resources.getString(R.string.thursday_abb)),
        RepeatingDayModel(false, resources.getString(R.string.friday_abb)),
        RepeatingDayModel(false, resources.getString(R.string.saturday_abb)),
        RepeatingDayModel(false, resources.getString(R.string.sunday_abb))
    )

}