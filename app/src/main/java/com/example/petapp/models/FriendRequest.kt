package com.example.petapp.models

import com.parse.ParseClassName
import com.parse.ParseFile
import com.parse.ParseObject
import com.parse.ParseUser

@ParseClassName("FriendRequest")
class FriendRequest : ParseObject() {

    fun getStatus(): String? {
        return getString(KEY_STATUS)
    }

    fun setStatus(description: String) {
        put(KEY_STATUS, description)
    }

    fun getSender(): ParseUser? {
        return getParseUser(KEY_SENDER)
    }

    fun setSender(user: ParseUser) {
        put(KEY_SENDER, user)
    }

    fun getReceiver(): ParseUser? {
        return getParseUser(KEY_RECEIVER)
    }

    fun setReceiver(user: ParseUser) {
        put(KEY_RECEIVER, user)
    }

    companion object {
        const val KEY_STATUS = "status"
        const val KEY_SENDER = "sender"
        const val KEY_RECEIVER = "receiver"
    }
}