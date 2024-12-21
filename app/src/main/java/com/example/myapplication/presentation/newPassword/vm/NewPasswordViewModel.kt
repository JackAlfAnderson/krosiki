package com.example.myapplication.presentation.newPassword.vm

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.supabase.BaseAuthManager
import com.example.myapplication.data.supabase.Profile
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class NewPasswordViewModel(val baseAuthManager: BaseAuthManager) : ViewModel() {

    val isShow = MutableStateFlow(false)

    fun newPassword(email: String, password:String){
        viewModelScope.launch {
            try {
                isShow.update {
                    true
                }
                baseAuthManager.newPassword(profile = Profile(email = email, password = password))
                isShow.update {
                    false
                }
            }catch (e:Exception){
                Log.d("newPasswordViewModelError", e.message.toString())
            }

        }
    }

}