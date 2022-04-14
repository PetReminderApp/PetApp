package com.example.petapp.models

import com.parse.ParseClassName
import com.parse.ParseFile
import com.parse.ParseObject
import org.json.JSONArray

@ParseClassName("Task")
class Task : ParseObject() {

    fun getName(): String? {
        return getString(KEY_NAME)
    }

    fun setName(name: String) {
        put(KEY_NAME, name)
    }

    fun getTasks(): JSONArray? {
        return getJSONArray(KEY_TASKS)
    }

    fun setTasks(tasks: JSONArray) {
        put(KEY_TASKS, tasks)
    }

    fun getPreferences(): String? {
        return getString(KEY_PREFERENCES)
    }

    fun setPreferences(preferences: String) {
        put(KEY_PREFERENCES, preferences)
    }

    fun getDescription(): String? {
        return getString(KEY_DESCRIPTION)
    }

    fun setDescription(description: String) {
        put(KEY_DESCRIPTION, description)
    }

    fun getPicture(): ParseFile? {
        return getParseFile(KEY_PICTURE)
    }

    fun setPicture(picture: ParseFile) {
        put(KEY_PICTURE, picture)
    }

    companion object {
        const val KEY_NAME = "name"
        const val KEY_TASKS = "tasks"
        const val KEY_PREFERENCES = "preferences"
        const val KEY_DESCRIPTION = "description"
        const val KEY_PICTURE = "picture"
    }
}