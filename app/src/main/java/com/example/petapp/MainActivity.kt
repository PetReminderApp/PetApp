package com.example.petapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.parse.ParseObject

class MainActivity : AppCompatActivity() {

    lateinit var bottomNav: BottomNavigationView
    lateinit var fab: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNav = findViewById(R.id.bottom_nav)
        fab = findViewById(R.id.fab_add)

        //Remove background shadow
        bottomNav.background = null

        val fragmentManager: FragmentManager = supportFragmentManager

        bottomNav.setOnItemSelectedListener { item ->
            var fragment: Fragment? = null

            //todo setup fragment navigation
//            when (item.itemId) {
//                R.id.action_home -> {
//                    fragment = HomeFragment()
//                }
//                R.id.action_compose -> {
//                    fragment = ComposeFragment()
//                }
//                R.id.action_profile -> {
//                    fragment = ProfileFragment()
//                }
//            }
//
//            if (fragment != null) {
//                fragmentManager.beginTransaction().replace(R.id.fragment_container_view, fragment).commit()
//            }

            //Return true to say that we've handled this user interaction
            true
        }
        //Set default fragment
        bottomNav.selectedItemId = R.id.action_home

        //todo
        fab.setOnClickListener {
            Toast.makeText(this, "Clicked Fab", Toast.LENGTH_SHORT).show()
        }
        //create Task Object and send to Parse
//        val testTask = ParseObject("Task")
//        testTask.put("title", "titleTest")
//        testTask.put("repeat", "repeatTest")
//        testTask.put("completed", true)
//
//        testTask.saveInBackground {
//            if (it != null){
//                it.localizedMessage?.let { message -> Log.e("MainActivity", message) }
//            }else{
//                Log.d("MainActivity","Object saved.")
//            }
//        }

    }
}