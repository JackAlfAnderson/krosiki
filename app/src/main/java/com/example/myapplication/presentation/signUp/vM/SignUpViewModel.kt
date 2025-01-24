package com.example.myapplication.presentation.signUp.vM

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.supabase.BaseAuthManager
import com.example.myapplication.domain.models.Profile
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SignUpViewModel(val baseAuthManager: BaseAuthManager): ViewModel() {

    val isShow = MutableStateFlow(false)

    fun signUp(name: String, email:String, password:String) = viewModelScope.launch {
        try {
            isShow.update {
                true
            }
            baseAuthManager.signUp(Profile(name = name, email = email, password = password))
            isShow.update {
                false
            }
        } catch (e: Exception) {
            Log.d("SignUpError", e.message.toString())
        }
    }
}