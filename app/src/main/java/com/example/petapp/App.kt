package com.example.petapp

import android.app.Application
import com.parse.Parse
import com.parse.ParseObject

class App : Application() {
    override fun onCreate() {

        //todo register Parse Models
//        ParseObject.registerSubclass(User::class.java)

        super.onCreate()
        Parse.initialize(
            Parse.Configuration.Builder(this)
                .applicationId(getString(R.string.back4app_app_id))
                .clientKey(getString(R.string.back4app_client_key))
                .server(getString(R.string.back4app_server_url))
                .build())
    }
}