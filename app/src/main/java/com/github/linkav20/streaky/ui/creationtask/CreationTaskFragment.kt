package com.github.linkav20.streaky.ui.creationtask

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.github.linkav20.streaky.R
import com.github.linkav20.streaky.databinding.FragmentCreationTaskBinding
import com.github.linkav20.streaky.ui.base.BaseFragment
import com.github.linkav20.streaky.ui.creationtask.model.RepeatingDayModel


class CreationTaskFragment : BaseFragment() {

    private val component by lazy { CreationTaskComponent.create() }

    private val viewModel by viewModels<CreationTaskViewModel> { component.viewModelFactory() }

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
            adapter.submitList(viewModel.getDaysListAbb(resources))
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
}