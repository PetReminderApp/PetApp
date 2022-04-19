package com.example.petapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.TimePicker
import com.example.petapp.R

class TaskComposeFragment : Fragment() {

    lateinit var spPets: Spinner
    lateinit var etTitle: EditText
    lateinit var etDescription: EditText
    lateinit var tvReminderTime: TextView
    lateinit var spRepeat: Spinner
    lateinit var timePicker: TimePicker

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
        timePicker = view.findViewById(R.id.timepicker)

    }

}