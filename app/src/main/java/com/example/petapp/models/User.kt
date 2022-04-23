package com.example.petapp.models

import com.parse.ParseUser

private const val KEY_EMAIL_VERIFIED = "emailVerified"
private const val KEY_PETS = "pets"
private const val KEY_USERNAME = "username"

fun ParseUser.getUsername(): String? {
    return getString(KEY_USERNAME)
}

fun ParseUser.getEmailVerified(): Boolean? {
    return getBoolean(KEY_EMAIL_VERIFIED)
}

fun ParseUser.setEmailVerified(emailVerified: Boolean) {
    put(KEY_EMAIL_VERIFIED, emailVerified)
}

fun ParseUser.getPets(): List<Pet>? {
    return getList(KEY_PETS)
}

fun ParseUser.setPets(pets: List<Pet>) {
    put(KEY_PETS, pets)
}