package com.example.petapp

import android.app.Application
import com.example.petapp.models.Friend
import com.example.petapp.models.FriendRequest
import com.example.petapp.models.Pet
import com.example.petapp.models.Task
import com.parse.Parse
import com.parse.ParseObject

class App : Application() {
    override fun onCreate() {

        //todo register Parse Models
        ParseObject.registerSubclass(Task::class.java)
        ParseObject.registerSubclass(Pet::class.java)
        ParseObject.registerSubclass(FriendRequest::class.java)
        ParseObject.registerSubclass(Friend::class.java)

        super.onCreate()
        Parse.initialize(
            Parse.Configuration.Builder(this)
                .applicationId(getString(R.string.back4app_app_id))
                .clientKey(getString(R.string.back4app_client_key))
                .server(getString(R.string.back4app_server_url))
                .build())
    }
}