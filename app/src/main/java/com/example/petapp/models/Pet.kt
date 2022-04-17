package com.example.petapp.models

import com.parse.ParseClassName
import com.parse.ParseFile
import com.parse.ParseObject

@ParseClassName("Pet")
class Pet : ParseObject() {

    fun getName() : String? {
        return getString(KEY_NAME)
    }

    fun setName(name : String){
        put(KEY_NAME, name)
    }

    fun getTasks() : List<Task>? {
        return getList(KEY_TASKS)
    }

    fun setTasks(tasks : List<Task>){
        put(KEY_TASKS, tasks)
    }

    fun getPicture() : ParseFile? {
        return getParseFile(KEY_PICTURE)
    }

    fun setPicture(parseFile: ParseFile){
        put(KEY_PICTURE, parseFile)
    }

    fun getDescription() : String? {
        return getString(KEY_DESCRIPTION)
    }

    fun setDescription(description : String){
        put(KEY_DESCRIPTION, description)
    }

    fun getPreferences() : String? {
        return getString(KEY_PREFERENCES)
    }

    fun setPreferences(preferences : String){
        put(KEY_PREFERENCES, preferences)
    }

    companion object {
        const val KEY_DESCRIPTION = "description"
        const val KEY_PICTURE = "picture"
        const val KEY_NAME = "name"
        const val KEY_TASKS = "tasks"
        const val KEY_PREFERENCES = "preferences"
    }

}