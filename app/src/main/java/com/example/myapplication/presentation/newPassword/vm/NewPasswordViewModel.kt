package com.example.myapplication.presentation.newPassword.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.supabase.BaseAuthManager
import com.example.myapplication.data.supabase.Profile
import kotlinx.coroutines.launch

class NewPasswordViewModel(val baseAuthManager: BaseAuthManager) : ViewModel() {

    fun newPassword(email: String, password:String){
        viewModelScope.launch {
            baseAuthManager.newPassword(profile = Profile(email = email, password = password))
        }
    }

}