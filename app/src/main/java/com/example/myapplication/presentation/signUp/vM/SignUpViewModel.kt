package com.example.myapplication.presentation.signUp.vM

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.supabase.BaseAuthManager
import com.example.myapplication.data.supabase.Profile
import kotlinx.coroutines.launch

class SignUpViewModel(val baseAuthManager: BaseAuthManager): ViewModel() {
    fun signUp(name: String, email:String, password:String) = viewModelScope.launch {
        try {
            baseAuthManager.signUp(Profile(name = name, email = email, password = password))
        } catch (e: Exception) {
            Log.d("SignUpError", e.message.toString())
        }
    }
}