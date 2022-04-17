package com.example.petapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.petapp.PetAdapter
import com.example.petapp.R
import com.example.petapp.models.Pet
import com.parse.FindCallback
import com.parse.ParseException
import com.parse.ParseQuery

class PetListFragment : Fragment() {

    lateinit var petRecyclerView : RecyclerView

    lateinit var adapter : PetAdapter

    var userPets : MutableList<Pet> = mutableListOf()

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

        val query : ParseQuery<Pet> = ParseQuery.getQuery(Pet::class.java)

        query.include(Pet.KEY_NAME)
        query.addDescendingOrder("createdAt")
        query.findInBackground(object : FindCallback<Pet> {
            override fun done(pets: MutableList<Pet>?, e: ParseException?) {
                if(e != null) {
                    // something went wrong
                    Log.e(TAG, "Error fetching posts")
                } else {
                    if (pets != null) {
                        for (pet in pets) {
                            Log.i(TAG, "Name: " + pet.getName() + " , Description: " + pet.getDescription())
                        }

                        userPets.addAll(pets)
                        adapter.notifyDataSetChanged()
                    }
                }
            }

        })
    }

    companion object {
        const val TAG = "PetListFragment"
    }


}