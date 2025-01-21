package com.example.myapplication

class ValidatorManager() {

    var isDialogShow = false

    fun emailValid(email:String) : Boolean{

        return false
    }

    fun passValid(password:String) : Boolean{

        return false
    }

    fun showDialog(message: String) : Boolean{
        isDialogShow = true
        return false
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

        return false
    }

}