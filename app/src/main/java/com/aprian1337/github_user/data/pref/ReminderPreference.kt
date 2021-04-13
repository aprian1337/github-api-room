package com.aprian1337.github_user.data.pref

import android.content.Context

internal class ReminderPreference(context: Context){
    companion object{
        private const val IS_ACTIVE = "is_active"
    }

    private val preferences = context.getSharedPreferences(IS_ACTIVE, Context.MODE_PRIVATE)

    fun setReminder(isActive : Boolean){
        val editor = preferences.edit()
        editor.putBoolean(IS_ACTIVE, isActive)
        editor.apply()
    }

    fun getReminder() : Boolean = preferences.getBoolean(IS_ACTIVE, false)
}