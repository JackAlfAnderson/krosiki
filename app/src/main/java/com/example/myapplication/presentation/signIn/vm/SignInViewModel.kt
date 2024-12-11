package com.example.myapplication.presentation.signIn.vm

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.myapplication.data.supabase.BaseAuthManager
import com.example.myapplication.data.supabase.Profile
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SignInViewModel(val baseAuthManager: BaseAuthManager) : ViewModel() {

    val isShow = MutableStateFlow(false)

    fun signIn(email: String, password: String){
        viewModelScope.launch {
            try {
                isShow.update { true }
                baseAuthManager.signIn(Profile(email = email, password = password))
                isShow.update { false }
            } catch (e: Exception) {
                Log.d("Error", e.message.toString())
            }
        }
    }

}