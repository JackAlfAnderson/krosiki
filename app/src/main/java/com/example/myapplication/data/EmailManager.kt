package com.example.myapplication.data

import android.content.Context
import androidx.core.content.edit

class EmailManager(context: Context) {

    val sharedPreferences = context.getSharedPreferences("email", Context.MODE_PRIVATE)

    fun set(email: String){
        sharedPreferences.edit{
            putString("emailGigant", email)
        }
    }

    fun get(): String{
        val email = sharedPreferences.getString("emailGigant", "empty")
        return email.toString()
    }
}