package com.example.petapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.petapp.R
import com.example.petapp.adapters.PetAdapter
import com.example.petapp.models.Pet
import com.example.petapp.models.getPets
import com.example.petapp.util.ParseUtil
import com.parse.FindCallback
import com.parse.ParseException
import com.parse.ParseQuery
import com.parse.ParseUser

class PetListFragment : Fragment() {

    lateinit var petRecyclerView: RecyclerView

    lateinit var adapter: PetAdapter

    var userPets: MutableList<Pet> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        petRecyclerView = view.findViewById(R.id.petListRV)

        adapter = PetAdapter(requireContext(), userPets)

        petRecyclerView.adapter = adapter
        petRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        queryPets()
    }

    open fun queryPets() {
        ParseUtil.queryPets({ pet ->
            userPets.add(pet)
            Log.i(TAG, "queryPets: added pet ${pet.getName()}")
            adapter.notifyItemInserted(userPets.size)
        })



    }

    companion object {
        const val TAG = "PetListFragment"
    }


}