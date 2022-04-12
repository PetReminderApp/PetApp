package com.example.petapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.parse.ParseObject

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //create Task Object and send to Parse
        val testTask = ParseObject("Task")
        testTask.put("title", "titleTest")
        testTask.put("repeat", "repeatTest")
        testTask.put("completed", true)

        testTask.saveInBackground {
            if (it != null){
                it.localizedMessage?.let { message -> Log.e("MainActivity", message) }
            }else{
                Log.d("MainActivity","Object saved.")
            }
        }
    }
}