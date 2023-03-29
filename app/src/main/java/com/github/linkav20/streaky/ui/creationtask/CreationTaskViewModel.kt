package com.github.linkav20.streaky.ui.creationtask

import android.content.Context
import android.content.res.Resources
import androidx.lifecycle.ViewModel
import com.github.linkav20.streaky.R
import com.github.linkav20.streaky.ui.creationtask.model.RepeatingDayModel
import javax.inject.Inject

class CreationTaskViewModel @Inject constructor(
    private val context: Context
) : ViewModel() {

    public fun getDaysListAbb(resources: Resources) = listOf(
        RepeatingDayModel(false, resources.getString(R.string.monday_abb)),
        RepeatingDayModel(true, resources.getString(R.string.tuesday_abb)),
        RepeatingDayModel(false, resources.getString(R.string.wednesday_abb)),
        RepeatingDayModel(false, resources.getString(R.string.thursday_abb)),
        RepeatingDayModel(false, resources.getString(R.string.friday_abb)),
        RepeatingDayModel(false, resources.getString(R.string.saturday_abb)),
        RepeatingDayModel(false, resources.getString(R.string.sunday_abb))
    )

}