package com.example.myapplication.presentation.editProfile.vm

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.supabase.BaseAuthManager
import com.example.myapplication.domain.models.Profile
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class EditProfileViewModel(private val baseAuthManager: BaseAuthManager): ViewModel() {

    val profile = MutableStateFlow(
        Profile(
        "",
        "",
        "",
        "",
        "",
        "",
        "",
    )
    )
    val isShow = MutableStateFlow(false)

    fun getProfile(email:String){
        try {
            viewModelScope.launch {
                isShow.update {
                    true
                }
                val userProfile = baseAuthManager.getProfile(email)

                isShow.update {
                    false
                }
                profile.update {
                    userProfile
                }
            }
        }catch (e: Exception){
            Log.d("error" , e.message.toString())
        }

    }

    fun updateProfile(profile: Profile){
        viewModelScope.launch {
            try {
                isShow.update {
                    true
                }
                baseAuthManager.updateProfile(profile)
                isShow.update {
                    false
                }
            } catch (e: Exception){
                Log.d("Es", e.message.toString())
            }


        }
    }
}