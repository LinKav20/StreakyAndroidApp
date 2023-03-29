package com.github.linkav20.streaky.ui.creationtask

import android.graphics.Outline
import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewOutlineProvider
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.github.linkav20.streaky.R
import com.github.linkav20.streaky.databinding.FragmentChangePasswordBinding
import com.github.linkav20.streaky.databinding.FragmentCreationTaskBinding
import com.github.linkav20.streaky.ui.base.BaseFragment
import com.github.linkav20.streaky.ui.creationtask.model.RepeatingDayModel
import eightbitlab.com.blurview.RenderScriptBlur

class CreationTaskFragment : BaseFragment() {

    private lateinit var binding: FragmentCreationTaskBinding

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

        val act = activity
        if (act != null) {
            val adapter = RepeatDayAdapter(binding, act.applicationContext, act.window)
            binding.repeatDayRecyclerview.adapter = adapter
            adapter.submitList(getDaysListAbb())
            BlurEffectInCreationFragment(
                binding.punishmentBlurview,
                binding,
                act.applicationContext,
                act.window
            )
        } else {
            // toast ex
            parentFragmentManager.popBackStack()
        }
    }

    private fun getDaysListAbb() = listOf(
        RepeatingDayModel(false, resources.getString(R.string.monday_abb)),
        RepeatingDayModel(true, resources.getString(R.string.tuesday_abb)),
        RepeatingDayModel(false, resources.getString(R.string.wednesday_abb)),
        RepeatingDayModel(false, resources.getString(R.string.thursday_abb)),
        RepeatingDayModel(false, resources.getString(R.string.friday_abb)),
        RepeatingDayModel(false, resources.getString(R.string.saturday_abb)),
        RepeatingDayModel(false, resources.getString(R.string.sunday_abb))
    )
}