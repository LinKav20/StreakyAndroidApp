package com.github.linkav20.streaky.ui.creationtask

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


class CreationTaskFragment : BaseFragment(), OnItemClickedListener  {

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

        val act = activity
        if (act != null) {
            val adapter = RepeatDayAdapter(this, act.applicationContext, act.window)
            binding.repeatDayRecyclerview.adapter = adapter
            viewModel.daysList.observe(viewLifecycleOwner) {
                adapter.submitList(it)
            }
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

        binding.createButton.setOnClickListener {
            findNavController().navigate(R.id.action_creation_fragment_to_mainFragment)
        }
    }

    override fun onItemClick(day: RepeatingDayModel) {
        viewModel.updateDaysList(day)
    }
}