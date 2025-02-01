package com.example.myapplication.presentation.profile.vm

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.supabase.BaseAuthManager
import com.example.myapplication.data.supabase.BasePostgrestManager
import com.example.myapplication.domain.models.Profile
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProfileViewModel(private val baseAuthManager: BaseAuthManager,private val basePostgrestManager: BasePostgrestManager): ViewModel() {

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

    val userImage = MutableStateFlow("")

    fun getImageUrl(userId: String) = viewModelScope.launch{
        userImage.update {
            basePostgrestManager.getUserImageUrl(userId)
        }
    }

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



}