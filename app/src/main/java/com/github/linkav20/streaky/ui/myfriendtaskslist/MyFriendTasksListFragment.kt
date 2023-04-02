package com.github.linkav20.streaky.ui.myfriendtaskslist

import android.app.Activity
import android.graphics.Canvas
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.github.linkav20.streaky.R
import com.github.linkav20.streaky.databinding.FragmentFriendsTasksListBinding
import com.github.linkav20.streaky.databinding.FragmentLoginBinding
import com.github.linkav20.streaky.databinding.FragmentTasksListBinding
import com.github.linkav20.streaky.ui.base.BaseFragment
import com.github.linkav20.streaky.ui.myfriendtaskslist.adapter.FriendTaskAdapter
import com.github.linkav20.streaky.ui.mytaskslist.MyTaskListViewModel
import com.github.linkav20.streaky.ui.mytaskslist.MyTasksListComponent
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator

class MyFriendTasksListFragment : BaseFragment() {

    private val component by lazy { MyFriendTasksListComponent.create() }

    private val viewModel by viewModels<MyFriendTaskListViewModel> { component.viewModelFactory() }

    lateinit var binding: FragmentFriendsTasksListBinding

    lateinit private var adapter: FriendTaskAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFriendsTasksListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setAdapter()
    }

    private fun setAdapter() {
        val act = activity
        if (act != null) {
            initAdapter(act)
        }
    }

    private fun initAdapter(activity: Activity) {
        adapter = FriendTaskAdapter(this, activity.applicationContext, activity.window)
        adapter.notifyDataSetChanged()
        binding.tasksRecyclerview.adapter = adapter
        viewModel.data.observe(viewLifecycleOwner) {
            adapter.items = it
        }
        initSwipeCallback()
    }

    private fun initSwipeCallback() {
        binding.apply {
            ItemTouchHelper(object :
                ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT) {

                override fun onChildDraw(
                    c: Canvas,
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    dX: Float,
                    dY: Float,
                    actionState: Int,
                    isCurrentlyActive: Boolean
                ) {
                    RecyclerViewSwipeDecorator.Builder(
                        c,
                        recyclerView,
                        viewHolder,
                        dX,
                        dY,
                        actionState,
                        isCurrentlyActive
                    )
                        .addBackgroundColor(context!!.getColor(R.color.white_opacity_40))
                        .addSwipeLeftActionIcon(R.drawable.ic_orange_star)
                        .addSwipeLeftLabel("notify")
                        .setSwipeLeftLabelColor(context!!.getColor(R.color.orange))
                        .setSwipeLeftLabelTypeface(context!!.resources.getFont(R.font.neue_machina_regular))
                        .addSwipeRightActionIcon(R.drawable.ic_lavender_star)
                        .addPadding(1, 10F, 10F, 10F)
                        .addCornerRadius(1, 10)
                        .setSwipeRightLabelTypeface(context!!.resources.getFont(R.font.neue_machina_regular))
                        .setSwipeRightLabelColor(context!!.getColor(R.color.lavender))
                        .addSwipeRightLabel("seen")
                        .create()
                        .decorate()

                    super.onChildDraw(
                        c,
                        recyclerView,
                        viewHolder,
                        dX,
                        dY,
                        actionState,
                        isCurrentlyActive
                    )
                }

                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    when (direction) {
                        ItemTouchHelper.RIGHT -> {
                            val needToNotify = viewModel.isSeen(viewHolder.bindingAdapterPosition)
                            if (!needToNotify) {
                                adapter.notifyItemChanged(viewHolder.bindingAdapterPosition)
                                viewModel.snackBar(root, "Not competes, cannot be seen")
                            } else
                                viewModel.snackBar(root, "Seen")
                        }
                        ItemTouchHelper.LEFT -> {
                            val needToNotify = viewModel.isNotify(viewHolder.bindingAdapterPosition)
                            adapter.notifyItemChanged(viewHolder.bindingAdapterPosition)
                            if (needToNotify) {
                                viewModel.snackBar(root, "Notify")
                            } else
                                viewModel.snackBar(root, "Notify is not nessasry")
                        }
                    }
                }
            }).attachToRecyclerView(tasksRecyclerview)
        }
    }
}