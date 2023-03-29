package com.github.linkav20.streaky.ui.creationtask

import android.content.Context
import android.graphics.Color
import android.graphics.Outline
import android.view.*
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.linkav20.streaky.R
import com.github.linkav20.streaky.databinding.FragmentCreationTaskBinding
import com.github.linkav20.streaky.databinding.RepeatingDayBinding
import com.github.linkav20.streaky.ui.creationtask.model.RepeatingDayModel
import eightbitlab.com.blurview.RenderScriptBlur

class RepeatDayAdapter(
    val fragment : FragmentCreationTaskBinding,
    val context: Context,
    val window: Window
) : ListAdapter<RepeatingDayModel, RepeatDayAdapter.RepeatDayViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepeatDayViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RepeatingDayBinding.inflate(inflater, parent, false)
        return RepeatDayViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RepeatDayViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    inner class RepeatDayViewHolder(
        private val binding: RepeatingDayBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(day: RepeatingDayModel) {
            with(binding) {
                dayButton.text = day.name

                if(day.chosen){
                    dayBlurview.setBackgroundColor(Color.parseColor("#FFFFFF"))
                }
                else {
                    BlurEffectInCreationFragment(dayBlurview, fragment, context, window)
                }
            }
        }
    }
}