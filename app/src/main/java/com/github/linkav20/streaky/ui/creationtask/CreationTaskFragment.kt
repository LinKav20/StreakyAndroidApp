package com.github.linkav20.streaky.ui.creationtask

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.github.linkav20.streaky.R
import com.github.linkav20.streaky.databinding.FragmentCreationTaskBinding
import com.github.linkav20.streaky.ui.base.BaseFragment
import com.github.linkav20.streaky.ui.creationtask.model.RepeatingDayModel
import com.github.linkav20.streaky.ui.creationtask.repeatdadyadapter.RepeatDayAdapter
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
        binding.doUntilButton.text = viewModel.getDateAfterMonth()

        binding.doUntilButton.setOnClickListener {
            datePicker()
        }

        binding.createButton.setOnClickListener {
            findNavController().navigate(R.id.action_creation_fragment_to_mainFragment)
        }
    }

    override fun onItemClick(day: RepeatingDayModel) {
        if (binding.notifySwitcher.isChecked) {
            viewModel.updateDaysList(day)
        } else {
            viewModel.snackBar(binding.root, resources.getString(R.string.cannot_chose_repeat))
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
                if (isChecked) {
                    notifyButton.text = viewModel.getCurrentTime()
                    notifyButton.visibility = View.VISIBLE
                } else {
                    viewModel.initStateOfRepeatingDays()
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
            BlurEffectInCreationFragment(
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
            val adapter = RepeatDayAdapter(this, act.applicationContext, act.window)
            binding.repeatDayRecyclerview.adapter = adapter
            viewModel.daysList.observe(viewLifecycleOwner) {
                adapter.submitList(it)
            }
        } else {
            viewModel.snackBar(binding.root, resources.getString(R.string.activity_null))
            parentFragmentManager.popBackStack()
        }
    }

    private fun timeDatePicker() {
        val c = Calendar.getInstance()
        val hour = c.get(Calendar.HOUR_OF_DAY)
        val minute = c.get(Calendar.MINUTE)

        val timePickerDialog = context?.let { ctx ->
            TimePickerDialog(
                ctx,
                { _, hourOfDay, minute -> binding.notifyButton.text = "$hourOfDay:$minute" },
                hour,
                minute,
                true
            )
        }

        timePickerDialog?.show()
    }

    private fun datePicker() {
        val c = Calendar.getInstance()
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
        val newDate = viewModel.getLongDateFromValues(yearD, monthD, dayD)

        if (newDate >= System.currentTimeMillis()) {
            val day = String.format("%02d", dayD)
            val month = String.format("%02d", monthD + 1)
            binding.doUntilButton.text = "$day.$month.$yearD"
        } else {
            viewModel.snackBar(binding.root, "Deadline cannot be earlier than now")
        }
    }
}