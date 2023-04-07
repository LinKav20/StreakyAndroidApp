package com.github.linkav20.streaky.ui.creationtask.notifications

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.github.linkav20.streaky.R
import com.github.linkav20.streaky.ui.creationtask.model.RepeatingDayModel
import java.util.*

object RemindersManager {
    const val REMINDER_NOTIFICATION_REQUEST_CODE = 123
    fun startReminder(
        context: Context,
        reminderTime: String = "12:00",
        reminderId: Int = REMINDER_NOTIFICATION_REQUEST_CODE,
        days: List<RepeatingDayModel> = listOf(
            RepeatingDayModel(false, context.resources.getString(R.string.monday_abb)),
            RepeatingDayModel(false, context.resources.getString(R.string.tuesday_abb)),
            RepeatingDayModel(false, context.resources.getString(R.string.wednesday_abb)),
            RepeatingDayModel(false, context.resources.getString(R.string.thursday_abb)),
            RepeatingDayModel(false, context.resources.getString(R.string.friday_abb)),
            RepeatingDayModel(false, context.resources.getString(R.string.saturday_abb)),
            RepeatingDayModel(false, context.resources.getString(R.string.sunday_abb))
        )
    ) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val (hours, min) = reminderTime.split(":").map { it.toInt() }
        val intent =
            Intent(context.applicationContext, AlarmReceiver::class.java).let { intent ->
                PendingIntent.getBroadcast(
                    context.applicationContext,
                    reminderId,
                    intent,
                    PendingIntent.FLAG_UPDATE_CURRENT
                )
            }

        val calendar: Calendar = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, hours)
            set(Calendar.MINUTE, min)
        }

        if (Calendar.getInstance()
                .apply { add(Calendar.MINUTE, 1) }.timeInMillis - calendar.timeInMillis > 0
        ) {
            calendar.add(Calendar.DATE, 1)
        }

        alarmManager.setAlarmClock(
            AlarmManager.AlarmClockInfo(calendar.timeInMillis, intent),
            intent
        )
    }

    fun stopReminder(
        context: Context,
        reminderId: Int = REMINDER_NOTIFICATION_REQUEST_CODE
    ) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, AlarmReceiver::class.java).let { intent ->
            PendingIntent.getBroadcast(
                context,
                reminderId,
                intent,
                0
            )
        }
        alarmManager.cancel(intent)
    }
}