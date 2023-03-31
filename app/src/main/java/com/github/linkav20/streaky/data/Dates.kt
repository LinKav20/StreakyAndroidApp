package com.github.linkav20.streaky.data

import java.text.SimpleDateFormat
import java.util.*

object Dates {
    fun getCurrentTime(): String {
        val sdf = SimpleDateFormat("HH:mm")
        return sdf.format(Date())
    }

    fun getCurrentDate(): String {
        val sdf = SimpleDateFormat("dd.MM.yyyy")
        return sdf.format(Date())
    }

    fun getMonthAfterCurrentDate(): String {
        val sdf = SimpleDateFormat("dd.MM.yyyy")
        val date = Date()
        val cal = Calendar.getInstance()
        cal.time = date
        cal.add(Calendar.MONTH, 1)
        return sdf.format(cal.time)
    }

    fun getLongDateFromValues(year: Int, month: Int, day: Int): Long {
        val calendar = Calendar.getInstance()
        calendar[Calendar.YEAR] = year
        calendar[Calendar.MONTH] = month
        calendar[Calendar.DAY_OF_MONTH] = day
        return calendar.timeInMillis
    }
}