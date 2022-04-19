package com.example.petapp.models

import com.parse.ParseClassName
import com.parse.ParseObject
import java.util.*

@ParseClassName("Task")
class Task : ParseObject() {

    fun getTitle(): String? {
        return getString(KEY_TITLE)
    }

    fun setTitle(title: String) {
        put(KEY_TITLE, title)
    }

    fun getCompleted(): Boolean? {
        return getBoolean(KEY_COMPLETED)
    }

    fun setCompleted(completed: Boolean) {
        put(KEY_COMPLETED, completed)
    }

    fun getTime(): Date? {
        return getDate(KEY_TIME)
    }

    fun setTime(time: Date) {
        put(KEY_TIME, time)
    }

    fun getDescription(): String? {
        return getString(KEY_DESCRIPTION)
    }

    fun setDescription(description: String) {
        put(KEY_DESCRIPTION, description)
    }

    fun getRepeat(): String? {
        return getString(KEY_REPEAT)
    }

    fun setRepeat(repeat: String) {
        put(KEY_REPEAT, repeat)
    }

    fun getPet(): Pet? {
        return createWithoutData(Pet::class.java, getParseObject(KEY_PET)?.objectId).fetchIfNeeded()
    }

    fun setPet(pet: Pet) {
        put(KEY_PET, pet)
    }

    fun getReminderTime(): String? {
        return getString(KEY_REMINDER_TIME)
    }

    fun setReminderTime(reminderTime: String) {
        put(KEY_REMINDER_TIME, reminderTime)
    }

    companion object {
        const val KEY_COMPLETED = "completed"
        const val KEY_TIME = "time"
        const val KEY_TITLE = "title"
        const val KEY_REPEAT = "repeat"
        const val KEY_DESCRIPTION = "description"
        const val KEY_PET = "pet"
        const val KEY_REMINDER_TIME = "reminderTime"
    }
}