package com.example.petapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.petapp.R
import com.example.petapp.adapters.UpcomingTasksAdapter
import com.example.petapp.models.Task

class HomeFragment : Fragment() {

    lateinit var rvTasks: RecyclerView
    lateinit var adapter: UpcomingTasksAdapter
    lateinit var tasks: MutableList<Task>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvTasks = view.findViewById(R.id.rv_tasks)
        adapter = UpcomingTasksAdapter(requireContext(), tasks)

        rvTasks.adapter = adapter
    }
}