package com.github.linkav20.streaky.ui.creationtask

import android.app.*
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.github.linkav20.streaky.R
import com.github.linkav20.streaky.databinding.FragmentCreationTaskBinding
import com.github.linkav20.streaky.ui.base.BaseFragment
import com.github.linkav20.streaky.ui.base.ImageType
import com.github.linkav20.streaky.ui.creationtask.model.RepeatingDayModel
import com.github.linkav20.streaky.ui.creationtask.notifications.RemindersManager
import com.github.linkav20.streaky.ui.creationtask.repeatdadyadapter.RepeatDayAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*


class CreationTaskFragment : BaseFragment(), OnItemClickedListener {

    private val component by lazy { CreationTaskComponent.create() }

    private val viewModel by viewModels<CreationTaskViewModel> { component.viewModelFactory() }

    lateinit var binding: FragmentCreationTaskBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreationTaskBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setRepeatDayAdapter()
        setBlurEffect()
        setNotifyBlock()
        setDoUntilBlock()
        setTitle()
        setPunishment()
        setFriendObserver()
        lifecycleScope.launch {
            setStrangerObserver()
        }

        binding.createButton.setOnClickListener {
            lifecycleScope.launch(Dispatchers.Main) {
                val message = withContext(Dispatchers.IO) {
                    viewModel.create()
                }
                viewModel.snackBar(binding.root, message)
                // TODO
                if (message == "ok") {
                    findNavController().navigate(R.id.action_creation_fragment_to_mainFragment)
                    if (binding.notifySwitcher.isChecked) setDailyNotify(binding.notifyButton.text.toString())
                }
            }
        }

        binding.backArrowButton.setOnClickListener {
            findNavController().navigate(R.id.action_creation_fragment_to_mainFragment)
        }
    }

    override fun onItemClick(day: RepeatingDayModel) {
        viewModel.updateDaysList(day)
    }

    private fun setTitle() {
        binding.titleEdittext.setText(viewModel.creationForm.value?.title ?: "")
        binding.titleEdittext.doOnTextChanged { text, _, _, _ ->
            viewModel.setTitleInCreationForm(text.toString())
        }
    }

    private fun setPunishment() {
        binding.punishmentEdittext.setText(viewModel.creationForm.value?.title ?: "")
        binding.punishmentEdittext.doOnTextChanged { text, _, _, _ ->
            viewModel.setPunishmentInCreationForm(text.toString())
        }
    }

    private fun setDoUntilBlock() {
        binding.doUntilButton.text = viewModel.getDeadlineAsString()

        binding.doUntilButton.setOnClickListener {
            datePicker()
        }
    }

    private fun setNotifyBlock() {
        with(binding) {
            setDefaultNotifyParams()
            setChangeNotifySwitcherState()
            setTimePicker()
        }
    }

    private fun setTimePicker() {
        binding.notifyButton.setOnClickListener {
            timeDatePicker()
        }
    }

    private fun setChangeNotifySwitcherState() {
        with(binding) {
            notifySwitcher.setOnCheckedChangeListener { _, isChecked ->
                viewModel.setNotifyInCreationForm(isChecked)

                if (isChecked) {
                    notifyButton.text = viewModel.getTimeForNotifyField()
                    notifyButton.visibility = View.VISIBLE
                } else {
                    notifyButton.visibility = View.GONE
                }
            }
        }
    }

    private fun setDefaultNotifyParams() {
        with(binding) {
            notifySwitcher.isChecked = false
            notifyButton.visibility = View.GONE
        }
    }

    private fun setBlurEffect() {
        val act = activity
        if (act != null) {
            blurEffectInCreationFragment(
                binding.punishmentBlurview,
                binding,
                act.applicationContext,
                act.window
            )
        } else {
            viewModel.snackBar(binding.root, resources.getString(R.string.activity_null))
            parentFragmentManager.popBackStack()
        }
    }

    private fun setRepeatDayAdapter() {
        val act = activity
        if (act != null) {
            initAdapter(act)
        } else {
            viewModel.snackBar(binding.root, resources.getString(R.string.activity_null))
            parentFragmentManager.popBackStack()
        }
    }

    private fun initAdapter(activity: Activity) {
        val adapter = RepeatDayAdapter(this, activity.applicationContext, activity.window)
        binding.repeatDayRecyclerview.adapter = adapter
        viewModel.creationForm.observe(viewLifecycleOwner) {
            adapter.submitList(it.repeat)
        }
    }

    private fun timeDatePicker() {
        val c = viewModel.getCalendarByDate(viewModel.creationForm.value?.notifyTime)
        val hour = c.get(Calendar.HOUR_OF_DAY)
        val minute = c.get(Calendar.MINUTE)

        val timePickerDialog = context?.let { ctx ->
            TimePickerDialog(
                ctx,
                { _, hourOfDay, minute ->
                    binding.notifyButton.text =
                        viewModel.getTimeFromHourAndMinuteAsString(hourOfDay, minute)
                },
                hour,
                minute,
                true
            )
        }

        timePickerDialog?.show()
    }

    private fun datePicker() {
        val c = viewModel.getCalendarByDate(viewModel.creationForm.value?.deadline)
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = context?.let { ctx ->
            DatePickerDialog(
                ctx,
                { _, year, monthOfYear, dayOfMonth ->
                    choseDayInCalendar(year, monthOfYear, dayOfMonth)
                },
                year,
                month,
                day
            )
        }
        datePickerDialog?.show()
    }

    private fun choseDayInCalendar(yearD: Int, monthD: Int, dayD: Int) {
        if (viewModel.isDateMoreThanNow(year = yearD, month = monthD, day = dayD)) {
            binding.doUntilButton.text =
                viewModel.getDateAsString(year = yearD, month = monthD, day = dayD)
        } else {
            viewModel.snackBar(binding.root, "Deadline cannot be earlier than now")
        }
    }

    private fun setFriendObserver() {
        binding.friendObserverEdittext.doOnTextChanged { text, _, _, _ ->
            lifecycleScope.launch(Dispatchers.Main) {
                binding.friendShimmerLayout.showShimmer(true)
                setLoadImage(binding.friendImageview)
                val result = withContext(Dispatchers.IO) {
                    viewModel.setFriendObserver(text.toString())
                }
                setImageOnResult(result, binding.friendImageview)
                binding.friendShimmerLayout.stopShimmer()
                binding.friendShimmerLayout.hideShimmer()
            }
        }
    }

    private suspend fun setStrangerObserver() {
        binding.strangerShimmerLayout.showShimmer(true)
        setLoadImage(binding.strangerImageview)
        val user = lifecycleScope.async {
            viewModel.getStranger()
        }.await()
        binding.strangerObserverEdittext.setText(user.login)
        setImageOnResult(true, binding.strangerImageview)
        binding.strangerShimmerLayout.stopShimmer()
        binding.strangerShimmerLayout.hideShimmer()
    }

    private fun setImageOnResult(result: Boolean, imageView: ImageView) {
        val type = if (result) ImageType.SUCCESS else ImageType.ERROR
        val image = viewModel.getImageByType(type, requireContext())
        viewModel.setResourceImageWithGlide(binding.root, image, imageView, 100)
    }

    private fun setLoadImage(imageView: ImageView) {
        val image = viewModel.getImageByType(ImageType.LOAD, requireContext())
        viewModel.setResourceImageWithGlide(binding.root, image, imageView, 100)
    }

    private fun setDailyNotify(value: String) {
        Log.d("WORK", "there ${value}")
        createNotificationsChannels()
        RemindersManager.startReminder(requireContext(), value)
    }

    private fun createNotificationsChannels() {
        val channel = NotificationChannel(
            "CHANNEL",
            "CHANNEL",
            NotificationManager.IMPORTANCE_HIGH
        )
        getSystemService(requireContext(), NotificationManager::class.java)
            ?.createNotificationChannel(channel)
    }
}
