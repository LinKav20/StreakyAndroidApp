package com.github.linkav20.streaky.ui.showmytask

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ScrollView
import androidx.navigation.fragment.findNavController
import com.applandeo.materialcalendarview.CalendarDay
import com.applandeo.materialcalendarview.EventDay
import com.applandeo.materialcalendarview.listeners.OnDayClickListener
import com.applandeo.materialcalendarview.listeners.OnDayLongClickListener
import com.github.linkav20.streaky.R
import com.github.linkav20.streaky.data.Dates
import com.github.linkav20.streaky.databinding.FragmentShowMyTaskBinding
import com.github.linkav20.streaky.ui.base.BaseFragment
import com.github.linkav20.streaky.ui.creationtask.blurEffectInShowTasksFragment
import java.util.*


class ShowMyTaskFragment : BaseFragment() {

    private lateinit var binding: FragmentShowMyTaskBinding

    private var taskId: Int = -1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShowMyTaskBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadArgs()
        //binding.text.text = "lollilol task with id: $taskId"
        binding.backArrowButton.setOnClickListener {
            findNavController().popBackStack()
        }

        setBlur()

        binding.scrollview.post { binding.scrollview.fullScroll(ScrollView.FOCUS_DOWN) }

        /* val calendar = Calendar.getInstance()
         calendar[Calendar.DAY_OF_MONTH] = 10

         binding.calendarView.setDate(calendar)
         val events = ArrayList<EventDay>()
         val c = Calendar.getInstance()
         c.set(2023,3,10)
        // binding.calendarView.setDate(c)
         events.add(EventDay(c, R.drawable.ic_lavender_star))


         val list = mutableListOf(
             CalendarDay(c).apply {
                 labelColor = R.color.white
                 backgroundResource = R.drawable.calendar_selector
                /* selectedLabelColor = [color resource]
                 selectedBackgroundResource = [drawable resource]
                 selectedBackgroundDrawable = [drawable]*/
             }
         )

         //events.add(EventDay(calendar, R.drawable.ic_lavender_star, Color.parseColor("#228B22")))
        // binding.calendarView.setEvents(events)
         binding.calendarView.setCalendarDays(list)
         //binding.calendarView.selectedDates = selected

         binding.calendarView.setOnDayClickListener(object : OnDayClickListener {
             override fun onDayClick(eventDay: EventDay) {
                 val clickedDayCalendar = eventDay.calendar
                 //binding.calendarView.setDate(clickedDayCalendar)
                 val cale = CalendarDay(clickedDayCalendar).apply {
                     labelColor = R.color.white
                     backgroundResource = R.drawable.calendar_selector
                     /* selectedLabelColor = [color resource]
                      selectedBackgroundResource = [drawable resource]
                      selectedBackgroundDrawable = [drawable]*/
                 }
                 //binding.calendarView.setDate(clickedDayCalendar)
                 list.add(cale)
                 binding.calendarView.setHighlightedDays(listOf(clickedDayCalendar))
                 Log.d("MY", "Click: ${Dates.convertDateToString(clickedDayCalendar.time)}")
             }
         })
         binding.calendarView.setOnDayLongClickListener(object : OnDayLongClickListener {
             override fun onDayLongClick(eventDay: EventDay) {
                 val clickedDayCalendar = eventDay.calendar
                 val cale = CalendarDay(clickedDayCalendar).apply {
                     labelColor = R.color.white
                     backgroundResource = R.drawable.calendar_selector
                     /* selectedLabelColor = [color resource]
                      selectedBackgroundResource = [drawable resource]
                      selectedBackgroundDrawable = [drawable]*/
                 }
                 //binding.calendarView.setDate(clickedDayCalendar)
                 list.add(cale)
                 binding.calendarView.setHighlightedDays(listOf(clickedDayCalendar))
                 Log.d("MY", "Long click: ${Dates.convertDateToString(clickedDayCalendar.time)}")
             }
         })*/
    }

    private fun setBlur() {
        val act = activity
        if (act != null) {
            blurEffectInShowTasksFragment(binding.infoBlurview, binding, act.applicationContext, act.window)
            blurEffectInShowTasksFragment(binding.punishmentBlurview, binding, act.applicationContext, act.window)
        }
    }

    private fun loadArgs() {
        val id = arguments?.getInt("id")
        if (id == null) {
            //snackBar(R.string.smth_goes_wrong)
            return
        }
        taskId = id
    }

}