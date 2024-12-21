package com.example.myapplication.presentation.forgotPassword.VM

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.supabase.BaseAuthManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ForgotPasswordViewModel(val baseAuthManager: BaseAuthManager) : ViewModel() {

    val isShow = MutableStateFlow(false)
    fun sendOTP(email: String){
        viewModelScope.launch {
            try {
                isShow.update {
                    true
                }
                baseAuthManager.sendOtp(email)
                isShow.update {
                    false
                }
            }catch (e:Exception){
                Log.d("forgotPasswordViewModelError" , e.message.toString())
            }

        }
    }

}