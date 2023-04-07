package com.github.linkav20.streaky.ui.showmytask

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ScrollView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.applandeo.materialcalendarview.CalendarDay
import com.applandeo.materialcalendarview.EventDay
import com.applandeo.materialcalendarview.listeners.OnDayClickListener
import com.github.linkav20.streaky.R
import com.github.linkav20.streaky.databinding.FragmentShowMyTaskBinding
import com.github.linkav20.streaky.ui.base.BaseFragment
import com.github.linkav20.streaky.ui.creationtask.blurEffectInShowTasksFragment
import com.github.linkav20.streaky.ui.base.ImageType
import com.github.linkav20.streaky.ui.showmytask.model.TaskUIModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch


class ShowMyTaskFragment : BaseFragment() {

    private val component by lazy { ShowMyTaskComponent.create() }

    private val viewModel by viewModels<ShowMyTaskViewModel> { component.viewModelFactory() }

    private lateinit var binding: FragmentShowMyTaskBinding

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
        binding.backArrowButton.setOnClickListener {
            findNavController().popBackStack()
        }
        setBlur()
        initCalendar()
    }

    private fun initCalendar() {
        setClickDate()
        observeShowingDay()
    }

    private fun observeShowingDay() {
        binding.strangerShimmerLayout.startShimmer()
        binding.friendShimmerLayout.startShimmer()
        viewModel.showDayData.observe(viewLifecycleOwner) {
            binding.dayTextview.text = it.date
            viewModel.setResourceImageWithGlide(
                binding.root, viewModel.getImageBySeen(it.friendStatus, requireContext()),
                binding.friendStatusImageview, 10
            )
            viewModel.setResourceImageWithGlide(
                binding.root, viewModel.getImageBySeen(it.strangerStatus, requireContext()),
                binding.strangerStatusImageview, 10
            )
            viewModel.setResourceImageWithGlide(
                binding.root, viewModel.getImageByStatus(it.taskStatus, requireContext()),
                binding.isDoneImage, 10
            )
            binding.strangerShimmerLayout.stopShimmer()
            binding.strangerShimmerLayout.hideShimmer()
            binding.friendShimmerLayout.stopShimmer()
            binding.friendShimmerLayout.hideShimmer()
        }
    }

    private fun setClickDate() {
        binding.calendarView.setOnDayClickListener(object : OnDayClickListener {
            override fun onDayClick(eventDay: EventDay) {
                val clickedDayCalendar = eventDay.calendar
                binding.calendarView.setHighlightedDays(listOf(clickedDayCalendar))
                viewModel.showInfoAboutDay(clickedDayCalendar.time)
                binding.scrollview.post { binding.scrollview.fullScroll(ScrollView.FOCUS_DOWN) }
            }
        })
    }

    private fun setDoneDates(list: List<CalendarDay>) {
        binding.calendarView.setCalendarDays(list)
    }

    private fun setBlur() {
        val act = activity
        if (act != null) {
            blurEffectInShowTasksFragment(
                binding.infoBlurview,
                binding,
                act.applicationContext,
                act.window
            )
            blurEffectInShowTasksFragment(
                binding.punishmentBlurview,
                binding,
                act.applicationContext,
                act.window
            )
        }
    }

    private fun loadArgs() {
        val id = arguments?.getInt("id")
        if (id == null) {
            viewModel.snackBar(
                binding.root,
                resources.getString(R.string.something_goes_wrong),
                resources
            )
            findNavController().popBackStack()
            return
        }

        lifecycleScope.launch {
            getTheTask(id)
        }
    }

    private suspend fun getTheTask(id: Int) {
        val task = lifecycleScope.async(Dispatchers.IO) {
            viewModel.getTaskInfo(id)
        }.await()

        setTaskFields(task)
    }

    private fun setTaskFields(task: TaskUIModel) {
        binding.titleTextview.text = task.title
        binding.doUntilButton.text = task.deadline
        binding.repeatButton.text = task.repeat
        binding.punishmentContentTextView.text = task.punishment
        binding.friendEdittext.setText(task.friendLogin)
        binding.strangerEdittext.setText(task.strangerLogin)
        setDoneDates(task.dates)
        viewModel.setResourceImageWithGlide(
            binding.root, viewModel.getImageByType(ImageType.SUCCESS, requireContext()),
            binding.strangerImageview, 100
        )
        viewModel.setResourceImageWithGlide(
            binding.root, viewModel.getImageByType(ImageType.SUCCESS, requireContext()),
            binding.friendImageview, 100
        )
    }
}