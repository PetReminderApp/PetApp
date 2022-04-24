package com.example.petapp.util

import android.util.Log
import com.example.petapp.models.Pet
import com.example.petapp.models.Task
import com.example.petapp.models.getPets
import com.parse.ParseQuery
import com.parse.ParseUser

private const val TAG = "ParseUtil"
object ParseUtil {

    fun queryPetTasks(pet: Pet, callback: (Task) -> Unit, onTasksDone: () -> Unit) {
        val taskPointers = pet.getTasks()

        taskPointers?.forEach { task ->
            callback(task.fetchIfNeeded())
        }

        onTasksDone()
    }

    fun queryPets(callback: (Pet) -> Unit) {
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