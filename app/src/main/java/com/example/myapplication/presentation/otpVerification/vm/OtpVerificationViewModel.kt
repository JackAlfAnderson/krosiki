package com.example.myapplication.presentation.otpVerification.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.supabase.BaseAuthManager
import kotlinx.coroutines.launch

class OtpVerificationViewModel(val baseAuthManager: BaseAuthManager): ViewModel() {
    fun verifyOtp(email: String, token: String){
        viewModelScope.launch {
            baseAuthManager.verifyOTP(email, token)
        }
    }
}