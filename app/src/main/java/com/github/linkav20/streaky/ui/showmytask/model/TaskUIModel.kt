package com.github.linkav20.streaky.ui.showmytask.model

import com.applandeo.materialcalendarview.CalendarDay

data class TaskUIModel(
    val title: String,
    val deadline: String,
    val repeat: String,
    val punishment: String,
    val dates: List<CalendarDay>,
    val friendLogin: String,
    val friendImage:String?,
    val strangerLogin: String,
    val strangerImage:String?
) {
}