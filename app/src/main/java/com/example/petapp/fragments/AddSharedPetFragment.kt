package com.example.petapp.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.example.petapp.FriendRequestAdapter
import com.example.petapp.R
import com.example.petapp.models.FriendRequest
import com.example.petapp.models.Pet
import com.parse.FindCallback
import com.parse.ParseException
import com.parse.ParseQuery
import com.parse.ParseUser

class AddSharedPetFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_shared_pet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // This is where we set up our views and click listeners

        activity?.findViewById<Button>(R.id.SubmitPR)?.setOnClickListener{
            var petnametext = activity?.findViewById<EditText>(R.id.PetNameET)?.text
            Log.i("Pet", petnametext.toString())



        }



    }
}