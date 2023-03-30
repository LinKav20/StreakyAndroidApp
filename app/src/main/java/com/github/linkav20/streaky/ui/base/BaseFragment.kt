package com.github.linkav20.streaky.ui.base

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.WindowManager
import androidx.fragment.app.Fragment
import com.github.linkav20.streaky.R

abstract class BaseFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflater = TransitionInflater.from(requireContext())
        exitTransition = inflater.inflateTransition(R.transition.fade)
        activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN or WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
    }
}