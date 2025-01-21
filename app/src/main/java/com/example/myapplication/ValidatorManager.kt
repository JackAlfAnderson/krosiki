package com.example.myapplication

import android.util.Patterns

class ValidatorManager() {

    var isDialogShow = false

    fun emailValid(email:String) : Boolean{

        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun passValid(password:String) : Boolean{

        return if (password.length > 6) true else false
    }

    fun showDialog(message: String){
        isDialogShow = true

    }

    fun login(email: String, password: String) : Boolean{

        if (!emailValid(email)){
            showDialog("Incorrect email")
            return false
        }
        if (!passValid(password)){
            showDialog("Incorrect password")
            return false
        }

        return true
    }

}