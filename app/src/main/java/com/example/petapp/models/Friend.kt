package com.example.petapp.models

import com.parse.ParseClassName
import com.parse.ParseObject
import com.parse.ParseUser

@ParseClassName("Friend")
class Friend  : ParseObject(){
    fun getFriend1(): ParseUser? {
        return getParseUser(KEY_FRIEND1)
    }

    fun setFriend1(user: ParseUser) {
        put(KEY_FRIEND1, user)
    }

    fun getFriend2(): ParseUser? {
        return getParseUser(KEY_FRIEND2)
    }

    fun setFriend2(user: ParseUser) {
        put(KEY_FRIEND2, user)
    }

    fun getSharedPets(): List<Pet>? {
        return getList(KEY_SHAREDPETS)
    }

    fun setSharedPets(pets: List<Pet>) {
        put(KEY_SHAREDPETS, pets)
    }

    companion object {
        const val KEY_FRIEND1 = "friend1"
        const val KEY_FRIEND2 = "friend2"
        const val KEY_SHAREDPETS = "sharedpets"
    }
}