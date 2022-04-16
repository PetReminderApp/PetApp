package com.example.petapp.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.petapp.R
import com.example.petapp.adapters.UpcomingTasksAdapter
import com.example.petapp.models.Task
import com.parse.ParseQuery

private const val TAG = "HomeFragment"

class HomeFragment : Fragment() {

    lateinit var rvTasks: RecyclerView
    lateinit var adapter: UpcomingTasksAdapter
    var tasks: MutableList<Task> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        queryTasks()

        rvTasks = view.findViewById(R.id.rv_tasks)
        adapter = UpcomingTasksAdapter(requireContext(), tasks)

        rvTasks.adapter = adapter
    }


    //Query for posts in our server
    fun queryTasks() {
        Log.i(TAG, "querying Tasks")

        // Specify which class to query
        val query: ParseQuery<Task> = ParseQuery.getQuery(Task::class.java)
//        query.include(Post.KEY_USER)
        // Return posts in descending order; newer posts appear 1st
//        query.addDescendingOrder("createdAt")

        //return 20 most recent posts
        query.limit = 20

        query.findInBackground { tasks, e ->
            if (e != null) {
                //Something went wrong
                Log.e(TAG, "Error fetching tasks")
            } else {
                if (tasks != null) {
                    for (task in tasks) {
                        Log.i(
                            TAG,
                            "Task: ${task.getTitle()}, Description: ${task.getDescription()} Pet: ${task.getPet().toString()}}"
                        )
                    }

                    this.tasks.addAll(tasks)
                    adapter.notifyDataSetChanged()
                    //Signal that refreshing has finished
//                    swipeContainer.isRefreshing = false
                }
            }
        }
    }

}