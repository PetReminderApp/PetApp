package com.example.petapp

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.petapp.fragments.HomeFragment
import com.example.petapp.fragments.PetListFragment
import com.example.petapp.fragments.TaskComposeFragment
import com.example.petapp.fragments.SocialFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    lateinit var bottomNav: BottomNavigationView
    lateinit var fab: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createNotificationChannel()

        bottomNav = findViewById(R.id.bottom_nav)
        fab = findViewById(R.id.fab_add)

        //Remove background shadow
        bottomNav.background = null

        val fragmentManager: FragmentManager = supportFragmentManager

        bottomNav.setOnItemSelectedListener { item ->
            var fragment: Fragment? = null

            //todo setup fragment navigation
            when (item.itemId) {
                R.id.action_home -> {
                    fragment = HomeFragment()
                }
                R.id.action_social -> {
                    fragment = SocialFragment()
                }
                R.id.action_pets -> {
                    fragment = PetListFragment()
                }

//                R.id.action_compose -> {
//                    fragment = ComposeFragment()
//                }
//                R.id.action_profile -> {
//                    fragment = ProfileFragment()
//                }
            }

            if (fragment != null) {
                fragmentManager.beginTransaction().replace(R.id.fragment_container_view, fragment).commit()
            }


            //Return true to say that we've handled this user interaction
            true
        }
        //Set default fragment
        bottomNav.selectedItemId = R.id.action_home

        fab.setOnClickListener {
            showAddItemDialog(fragmentManager)
        }
    }

    private fun showAddItemDialog(fragmentManager: FragmentManager) {
        val bottomSheetDialog = BottomSheetDialog(this)
        bottomSheetDialog.setContentView(R.layout.bottom_sheet_add)

        val addTask = bottomSheetDialog.findViewById<ConstraintLayout>(R.id.card_add_task)
        val addPet = bottomSheetDialog.findViewById<ConstraintLayout>(R.id.card_add_pet)

        //todo navigate to Task Compose Screen
        addTask?.setOnClickListener {
            Log.d(TAG, "Clicked Add Task")
            //todo verify user has a Pet already

            fragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container_view, TaskComposeFragment())
                .addToBackStack(null)
                .commit()

            bottomSheetDialog.dismiss()
        }

        //todo navigate to Pet Compose Screen
        addPet?.setOnClickListener {
            Log.d(TAG, "Clicked Add Pet")

            bottomSheetDialog.dismiss()
        }

        bottomSheetDialog.show()
    }

    private fun createNotificationChannel() {
        val currTime = System.currentTimeMillis()

        val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, AlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0)



        alarmManager.set(AlarmManager.RTC_WAKEUP, currTime, pendingIntent)

//        alarmManager.setRepeating(
//            AlarmManager.RTC_WAKEUP,
//            currTime,
//            AlarmManager.INTERVAL_HOUR * 2,
//            pendingIntent
//        )
    }
}