package com.example.petapp.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.example.petapp.R
import com.example.petapp.models.Pet
import com.parse.ParseUser
import java.io.File

// TODO: Rename parameter arguments, choose names that match




class AddPetFragment : Fragment() {
    // TODO: Rename and change types of parameters
    var photoFile: File? = null

    lateinit var ivPreview: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_pet, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Set onclicklisteners and setup logic
        view.findViewById<Button>(R.id.btnSubmit).setOnClickListener {
            //Grab the name
            val myName=view.findViewById<EditText>(R.id.etPetName).text.toString()
            val myDesc=view.findViewById<EditText>(R.id.etPetDescription).text.toString()

            createPet(myName)
        }




    }
        //Should I use default arguments ? foo: String = "Default text"
        //Optional: Desc and Img
    fun createPet(name: String,desc: String=""){
        val pet= Pet()
        //Grab the name for our pet

        //I haven't used setUser yet (where Parseuser is passed in as an argument) Seems like that's not necessary tho for this ?
        pet.setName(name);
            if(desc!=""){
                pet.setDescription(desc)
            }

        pet.saveInBackground {exception ->
            if(exception!=null){
                Log.e("ComposeFrag","Error while saving Pet")
                exception.printStackTrace()
                Toast.makeText(requireContext(),"Something went wrong with saving this pet", Toast.LENGTH_SHORT).show()
            }else {
                Toast.makeText(requireContext(),"Pet Saved", Toast.LENGTH_SHORT).show()
            }

        }


        //
    }

    companion object {

    }

}