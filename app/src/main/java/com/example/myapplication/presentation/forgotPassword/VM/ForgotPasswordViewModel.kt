package com.example.myapplication.presentation.forgotPassword.VM

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.supabase.BaseAuthManager
import kotlinx.coroutines.launch

class ForgotPasswordViewModel(val baseAuthManager: BaseAuthManager) : ViewModel() {

    fun sendOTP(email: String){
        viewModelScope.launch {
            baseAuthManager.sendOtp(email)
        }
    }

}