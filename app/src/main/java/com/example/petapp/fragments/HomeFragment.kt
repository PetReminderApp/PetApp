package com.example.petapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.petapp.R
import com.example.petapp.adapters.UpcomingTasksAdapter
import com.example.petapp.models.Pet
import com.example.petapp.models.Task
import com.example.petapp.models.getPets
import com.parse.ParseQuery
import com.parse.ParseUser

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

        //query Tasks from Parse and load them into RecyclerView
        queryTasks()

        rvTasks = view.findViewById(R.id.rv_tasks)
        adapter = UpcomingTasksAdapter(requireContext(), tasks)

        rvTasks.adapter = adapter
    }

    //Query for posts in our server
    private fun queryTasks() {
        queryPets { pet ->
            //iterate over task pointers and append to tasks
            queryPetTasks(pet) { task ->
                tasks.add(task)
                adapter.notifyItemInserted(tasks.size)
            }
        }
    }

    private fun queryPetTasks(pet: Pet, callback: (Task) -> Unit) {
        val taskPointers = pet.getTasks()

        taskPointers?.forEach { task ->
            callback(task.fetchIfNeeded())
        }
    }

    private fun queryPets(callback: (Pet) -> Unit) {
        //object ID of currently signed in User
        val userObjId = ParseUser.getCurrentUser().objectId

        // Specify which class to query
        val query: ParseQuery<ParseUser> = ParseQuery.getQuery(ParseUser::class.java)
        //query current user by its objectID
        query.whereEqualTo("objectId", userObjId)

        query.getFirstInBackground { user, e ->
            if (e != null) {
                //Something went wrong
                Log.e(TAG, "Error fetching User")
            } else {
                if (user != null) {
                    //get pets from user
                    val userPetsPointers = user.getPets()

                    //iterate over all Pets
                    userPetsPointers?.forEach { pet ->
                        callback(pet.fetchIfNeeded())
                    }
                }
            }
        }
    }

}