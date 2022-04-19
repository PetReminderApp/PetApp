package com.example.petapp.fragments

import android.app.TimePickerDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import com.example.petapp.R
import com.example.petapp.models.Task

private const val TAG = "TaskComposeFragment"

class TaskComposeFragment : Fragment() {

    lateinit var spPets: Spinner
    lateinit var etTitle: EditText
    lateinit var etDescription: EditText
    lateinit var tvReminderTime: TextView
    lateinit var spRepeat: Spinner
    lateinit var btSave: Button
    val timePickerDialogListener: TimePickerDialog.OnTimeSetListener =
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

        tvReminderTime.setOnClickListener {
            Log.d(TAG, "Reminder time clicked")

            val timePicker: TimePickerDialog = TimePickerDialog(
                // pass the Context
                requireContext(),
                // listener to perform task when time is picked
                timePickerDialogListener,
                // default hour when the time picker dialog is opened
                12,
                // default minute when the time picker dialog is opened
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
            val title = etTitle.text.toString()
            val description = etDescription.text.toString()
            val reminderTime = tvReminderTime.text.toString()
            //todo 1. Validate input

            //todo 2. Create Object
            val task = Task().apply {
                setTitle(title)
                setDescription(description)
            }

            //todo 3. Send object to Parse
        }

    }

}