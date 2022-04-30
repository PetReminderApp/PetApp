package com.example.petapp.fragments

import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.petapp.R
import com.example.petapp.adapters.UpcomingTasksAdapter
import com.example.petapp.models.Task
import com.example.petapp.util.ParseUtil
import com.example.petapp.util.RecyclerItemTouchHelper


private const val TAG = "HomeFragment"

class HomeFragment : Fragment() {

    private lateinit var rvTasks: RecyclerView
    private lateinit var adapter: UpcomingTasksAdapter
    private lateinit var loadingDialog: CardView
    private var tasks: MutableList<Task> = mutableListOf()
    private val itemTouchHelperListener =
        RecyclerItemTouchHelper.RecyclerItemTouchHelperListener { viewHolder, direction, position ->
            if (viewHolder is UpcomingTasksAdapter.ViewHolder) {
                Log.d(TAG, "Marking Task complete")
                //Mark Task complete
                val completedTask = tasks[viewHolder.adapterPosition]
                completedTask.setCompleted(true)
                completedTask.saveInBackground()

                //Remove Task from adapter
                adapter.removeItem(viewHolder.adapterPosition) //remove the item from the adapter
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadingDialog = view.findViewById(R.id.loading_dialog)
        rvTasks = view.findViewById(R.id.rv_tasks)

        //if returning from another fragment, tasks should be cleared then updated
        tasks.clear()

        loadingDialog.visibility = View.VISIBLE

        //query Tasks from Parse and load them into RecyclerView
        queryTasks()

        adapter = UpcomingTasksAdapter(requireContext(), tasks)

        rvTasks.itemAnimator = DefaultItemAnimator()
        rvTasks.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
        val itemTouchHelperCallback: ItemTouchHelper.SimpleCallback =
            RecyclerItemTouchHelper(
                requireContext(),
                0,
                ItemTouchHelper.RIGHT,
                itemTouchHelperListener
            )
        ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(rvTasks)
        rvTasks.adapter = adapter

    }

    //Query for posts in our server
    private fun queryTasks() {
        ParseUtil.queryPets(
            callback = { pet ->
                //iterate over task pointers and append to tasks
                ParseUtil.queryPetTasks(
                    pet,
                    callback = { task ->
                        if (task.getCompleted() == false) {
                            tasks.add(task)
                            adapter.notifyItemInserted(tasks.size)
                        }
                    },
                    onTasksDone = {
                        loadingDialog.visibility = View.GONE
                    }
                )
            }, onQueryDone = {
                loadingDialog.visibility = View.GONE
            }
        )
    }

}