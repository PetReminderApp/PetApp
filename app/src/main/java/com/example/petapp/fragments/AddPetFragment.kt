package com.example.petapp.fragments

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.core.graphics.drawable.toBitmap
import androidx.fragment.app.Fragment
import com.example.petapp.R
import com.example.petapp.models.Pet
import com.parse.ParseFile
import java.io.ByteArrayOutputStream
import java.io.File


// TODO: Rename parameter arguments, choose names that match




class AddPetFragment : Fragment() {
    // TODO: Rename and change types of parameters
    var photoFile: File? = null
    lateinit var ivPetImage: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_pet, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        ivPetImage=view.findViewById<ImageView>(R.id.ivPetImage)
       //Glide.with(this)
        //.load(R.drawable.petdefault)
        //.into(myImg);

        //Set onclicklisteners and setup logic
        view.findViewById<Button>(R.id.btnSubmit).setOnClickListener {
            //Grab the name
            val myName=view.findViewById<EditText>(R.id.etPetName).text.toString()
            val myDesc=view.findViewById<EditText>(R.id.etPetDescription).text.toString()
            val myplaceholder=view.findViewById<ImageView>(R.id.ivPetImage).drawable.toBitmap(300,300)
            val myImg=conversionBitmapParseFile(myplaceholder)
            createPet(myName,myDesc,myImg)
        }
        view.findViewById<ImageView>(R.id.ivPetImage).setOnClickListener{
      //  view.findViewById<Button>(R.id.btnGlide).setOnClickListener {
            /*
            Toast.makeText(requireContext(),"Glidebutton", Toast.LENGTH_SHORT).show()
            Glide.with(this).load("http://via.placeholder.com/300.png").into(myImg)
            */
            val pickPhoto = Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            )
            startActivityForResult(pickPhoto, 1)

        }


    }
        //Should I use default arguments ? foo: String = "Default text"
        //Optional: Desc and Img
    fun createPet(name: String,desc: String="",img :ParseFile?=null){
        val pet= Pet()
        //Grab the name for our pet

        //I haven't used setUser yet (where Parseuser is passed in as an argument) Seems like that's not necessary tho for this ?
        pet.setName(name);
            if(desc!=""){
                pet.setDescription(desc)
            }
            if(img!=null){
                pet.setPicture(img)
            }

        pet.saveInBackground {exception ->
            if(exception!=null){
                Log.e("ComposeFrag","Error while saving Pet")
                exception.printStackTrace()
                Toast.makeText(requireContext(),"Something went wrong with saving this pet", Toast.LENGTH_SHORT).show()
            }else {
                Toast.makeText(requireContext(),"Pet Saved", Toast.LENGTH_SHORT).show()
                activity?.supportFragmentManager?.popBackStack()
            }

        }


        //
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, imageReturnedIntent: Intent?) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent)
        when (requestCode) {
            0 -> if (resultCode == RESULT_OK) {
                val selectedImage: Uri? = imageReturnedIntent?.data
                ivPetImage.setImageURI(selectedImage)
            }
            1 -> if (resultCode == RESULT_OK) {
                val selectedImage: Uri? = imageReturnedIntent?.data
                ivPetImage.setImageURI(selectedImage)
            }
        }
    }
    fun conversionBitmapParseFile(imageBitmap: Bitmap): ParseFile? {
        val byteArrayOutputStream = ByteArrayOutputStream()
        imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
        val imageByte: ByteArray = byteArrayOutputStream.toByteArray()
        return ParseFile("image_file.png", imageByte)
    }

    companion object {

    }

}