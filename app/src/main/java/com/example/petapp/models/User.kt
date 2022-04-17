package com.example.petapp.models

import com.parse.ParseClassName
import com.parse.ParseObject
import com.parse.ParseUser
import org.json.JSONArray
import java.util.*

private const val KEY_EMAIL_VERIFIED = "emailVerified"
private const val KEY_PETS = "pets"

fun ParseUser.getEmailVerified(): Boolean? {
    return getBoolean(KEY_EMAIL_VERIFIED)
}

fun ParseUser.setEmailVerified(emailVerified: Boolean) {
    put(KEY_EMAIL_VERIFIED, emailVerified)
}

fun ParseUser.getPets(): JSONArray? {
    return getJSONArray(KEY_PETS)
}

fun ParseUser.setPets(pets: JSONArray) {
    put(KEY_PETS, pets)
}