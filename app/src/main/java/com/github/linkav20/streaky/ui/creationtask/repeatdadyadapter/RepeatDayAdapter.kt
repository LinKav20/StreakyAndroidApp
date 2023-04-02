package com.github.linkav20.streaky.ui.creationtask.repeatdadyadapter

import android.content.Context
import android.view.*
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.linkav20.streaky.databinding.RepeatingDayBinding
import com.github.linkav20.streaky.databinding.RepeatingDayChosenBinding
import com.github.linkav20.streaky.ui.creationtask.blurEffectInCreationFragment
import com.github.linkav20.streaky.ui.creationtask.CreationTaskFragment
import com.github.linkav20.streaky.ui.creationtask.model.RepeatingDayModel

class RepeatDayAdapter(
    val fragment: CreationTaskFragment,
    val context: Context,
    val window: Window
) : ListAdapter<RepeatingDayModel, RecyclerView.ViewHolder>(DiffCallback()) {

    private val TYPE_CHOSEN = 1
    private val TYPE_NOT_CHOSEN = 0

    override fun getItemViewType(position: Int): Int {
        val currentItem = getItem(position)
        return if (currentItem.chosen) TYPE_CHOSEN else TYPE_NOT_CHOSEN
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            TYPE_CHOSEN -> {
                val binding = RepeatingDayChosenBinding.inflate(inflater, parent, false)
                RepeatDayChosenViewHolder(binding)
            }
            else -> {
                val binding = RepeatingDayBinding.inflate(inflater, parent, false)
                RepeatDayViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentItem = getItem(position)
        when (holder.itemViewType) {
            TYPE_NOT_CHOSEN -> (holder as RepeatDayViewHolder).bind(currentItem)
            TYPE_CHOSEN -> (holder as RepeatDayChosenViewHolder).bind(currentItem)
        }
    }

    inner class RepeatDayViewHolder(
        private val binding: RepeatingDayBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(day: RepeatingDayModel) {
            with(binding) {
                dayButton.text = day.name
                blurEffectInCreationFragment(dayBlurview, fragment.binding, context, window)

                binding.mainLayout.setOnClickListener {
                    fragment.onItemClick(day.copy(chosen = true))
                }
            }
        }
    }

    inner class RepeatDayChosenViewHolder(
        private val binding: RepeatingDayChosenBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(day: RepeatingDayModel) {
            with(binding) {
                dayButton.text = day.name

                binding.mainLayout.setOnClickListener {
                    fragment.onItemClick(day.copy(chosen = false))
                }
            }
        }
    }
}