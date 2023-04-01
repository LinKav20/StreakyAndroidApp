package com.github.linkav20.streaky.data

import java.text.SimpleDateFormat
import java.util.*

object Dates {
    private val sdfTime = SimpleDateFormat("HH:mm")
    private val sdfDate = SimpleDateFormat("dd.MM.yyyy")

    fun getCurrentTimeAsString(): String {
        return sdfTime.format(Date())
    }

    fun getCurrentTime(): Date {
        return Date()
    }

    fun getCurrentDateAsString(): String {
        return sdfDate.format(Date())
    }

    fun getMonthAfterCurrentDateAsString() = sdfDate.format(getMonthAfterCurrentDate())

    fun getMonthAfterCurrentDate(): Date {
        val cal = Calendar.getInstance()
        cal.add(Calendar.MONTH, 1)
        return cal.time
    }

    fun getLongDateFromValues(year: Int, month: Int, day: Int): Long {
        val calendar = Calendar.getInstance()
        calendar[Calendar.YEAR] = year
        calendar[Calendar.MONTH] = month
        calendar[Calendar.DAY_OF_MONTH] = day
        return calendar.timeInMillis
    }

    fun getTimeFromHourAndMinute(hour: Int, minute: Int): Date {
        val cal = Calendar.getInstance()
        cal[Calendar.HOUR_OF_DAY] = hour
        cal[Calendar.MINUTE] = minute
        return cal.time
    }

    fun convertTimeToString(date: Date) = sdfTime.format(date)

    fun getDateFromDMY(day: Int, month: Int, year: Int): Date {
        val cal = Calendar.getInstance()
        cal[Calendar.DAY_OF_MONTH] = day
        cal[Calendar.MONTH] = month
        cal[Calendar.YEAR] = year
        return cal.time
    }

    fun convertDateToString(date: Date) = sdfDate.format(date)

    fun getCalendarByDate(date: Date?): Calendar {
        val c = Calendar.getInstance()
        if (date != null) c.time = date
        return c
    }

    fun isDateMoreThanNow(date: Date): Boolean =
        date.time > System.currentTimeMillis()

}