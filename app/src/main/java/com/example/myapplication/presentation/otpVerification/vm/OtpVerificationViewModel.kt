package com.example.myapplication.presentation.otpVerification.vm

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.supabase.BaseAuthManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class OtpVerificationViewModel(val baseAuthManager: BaseAuthManager): ViewModel() {
    val isShow = MutableStateFlow(false)

    fun verifyOtp(email: String, token: String){
        viewModelScope.launch {
            try {
                isShow.update {
                    true
                }

                baseAuthManager.verifyOTP(email, token)
                isShow.update {
                    false
                }
            } catch (e:Exception){
                Log.d("otpVerificationViewModelError", e.message.toString())
            }

        }
    }
}