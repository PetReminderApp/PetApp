package com.example.petapp.fragments

import android.R.layout.simple_spinner_item
import android.app.TimePickerDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.petapp.R
import com.example.petapp.models.Pet
import com.example.petapp.models.Task
import com.example.petapp.util.ParseUtil
import java.util.*

private const val TAG = "TaskComposeFragment"

class TaskComposeFragment : Fragment() {

    private lateinit var spPets: Spinner
    private lateinit var etTitle: EditText
    private lateinit var etDescription: EditText
    private lateinit var tvReminderTime: TextView
    private lateinit var spRepeat: Spinner
    private lateinit var btSave: Button

    private val userPets = mutableListOf<Pet>()
    private val petNames = mutableListOf<String>()
    private lateinit var arrayAdapter: ArrayAdapter<String>
    private val timePickerDialogListener: TimePickerDialog.OnTimeSetListener =
        TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute -> // logic to properly handle
            // the picked timings by user
            val formattedTime: String = when {
                hourOfDay == 0 -> {
                    if (minute < 10) {
                        "${hourOfDay + 12}:0${minute} am"
                    } else {
                        "${hourOfDay + 12}:${minute} am"
                    }
                }
                hourOfDay > 12 -> {
                    if (minute < 10) {
                        "${hourOfDay - 12}:0${minute} pm"
                    } else {
                        "${hourOfDay - 12}:${minute} pm"
                    }
                }
                hourOfDay == 12 -> {
                    if (minute < 10) {
                        "${hourOfDay}:0${minute} pm"
                    } else {
                        "${hourOfDay}:${minute} pm"
                    }
                }
                else -> {
                    if (minute < 10) {
                        "${hourOfDay}:${minute} am"
                    } else {
                        "${hourOfDay}:${minute} am"
                    }
                }
            }

            tvReminderTime.text = formattedTime
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_task_compose, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        spPets = view.findViewById(R.id.sp_pets)
        etTitle = view.findViewById(R.id.et_title)
        etDescription = view.findViewById(R.id.et_description)
        tvReminderTime = view.findViewById(R.id.tv_reminder_time)
        spRepeat = view.findViewById(R.id.sp_repeat)
        btSave = view.findViewById(R.id.bt_save)

        //populate the Pets Spinner with all of the User's Pet Names
        populatePetsSpinner()

        tvReminderTime.setOnClickListener {
            Log.d(TAG, "Reminder time clicked")

            val timePicker: TimePickerDialog = TimePickerDialog(
                requireContext(),
                // listener to perform task when time is picked
                timePickerDialogListener,
                // default hour
                12,
                // default minute
                10,
                // 24 hours time picker is false (varies according to the region)
                false
            )

            // then after building the timepicker
            // dialog show the dialog to user
            timePicker.show()
        }

        //send the task to Parse
        btSave.setOnClickListener {
            val selectedPet = userPets[spPets.selectedItemPosition]
            val title = etTitle.text.toString()
            val description = etDescription.text.toString()
            val reminderTime = tvReminderTime.text.toString()
            val repeat = spRepeat.selectedItem.toString()

            Log.d(
                TAG, "\n" + listOf(
                    "Name: ${selectedPet.getName()}",
                    "Title: $title",
                    "Description: $description",
                    "Reminder Time: $reminderTime",
                    "Repeat: $repeat"
                ).joinToString(",\n")
            )

            //1. Validate input
            //if input is not valid, show Toast and return
            if (!inputIsValid(title, description, reminderTime, repeat)) {
                Toast.makeText(requireContext(), "Invalid Input!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            //2. Create Object
            val task = Task().apply {
                setTitle(title)
                setDescription(description)
                setPet(selectedPet)
                setRepeat(repeat)
                setReminderTime(reminderTime)
                setTime(Date())
            }

            //3. Send object to Parse
            task.saveInBackground().onSuccess {
                Log.d(TAG, "Saved Task successfully")

                //todo 4. Update Pet Task Pointer Array
                //a) get Pet Task Pointer Array
                val petTaskPointers = selectedPet.getTasks()?.toMutableList()

                Log.d(TAG, "petTaskPointers is $petTaskPointers") // is this null? Can't add item to list if null...
                
                //b) Update Pointer Array
                petTaskPointers?.add(task)
                selectedPet.setTasks(petTaskPointers?.toList() ?: listOf(task))

                //c) POST new pointer Array
                selectedPet.saveInBackground()

                //pop backstack
                activity?.supportFragmentManager?.popBackStack()
            }
        }
    }

    private fun inputIsValid(
        title: String,
        description: String,
        reminderTime: String,
        repeat: String
    ): Boolean {
        //must have title and repeat.
        if (title.isBlank() || reminderTime.isBlank()) return false
        return true
    }

    private fun populatePetsSpinner() {
        arrayAdapter = ArrayAdapter(requireContext(), simple_spinner_item, petNames)

        //populates userPets, petNames, and the arrayAdapter
        ParseUtil.queryPets { pet ->
            pet.getName()?.let {
                Log.d(TAG, "Adding $it to petNames")
                userPets.add(pet)
                petNames.add(it)
                arrayAdapter.notifyDataSetChanged()
            }
        }
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spPets.adapter = arrayAdapter
    }

}