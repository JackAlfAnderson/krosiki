package com.example.myapplication.presentation.sideMenu.vm

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.supabase.BaseAuthManager
import com.example.myapplication.data.supabase.BasePostgrestManager
import com.example.myapplication.domain.models.Profile
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SideMenuViewModel(private val baseAuthManager: BaseAuthManager, private val basePostgrestManager: BasePostgrestManager): ViewModel() {

    var profile = MutableStateFlow(Profile())
    var isShow = MutableStateFlow(false)

    val userImage = MutableStateFlow("")

    fun getImageUrl(userId: String) = viewModelScope.launch{
        userImage.update {
            basePostgrestManager.getUserImageUrl(userId)
        }
    }


    fun getProfile(email:String) = viewModelScope.launch{

        try {
            isShow.update {
                true
            }
            val userProfile = baseAuthManager.getProfile(email)
            profile.update {
                userProfile
            }
            isShow.update {
                false
            }
        }catch (e: Exception){
            Log.d("error" , e.message.toString())
        }

    }

}