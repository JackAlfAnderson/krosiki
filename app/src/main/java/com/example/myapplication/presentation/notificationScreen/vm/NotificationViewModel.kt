package com.example.myapplication.presentation.notificationScreen.vm

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.EmailManager
import com.example.myapplication.data.supabase.BasePostgrestManager
import com.example.myapplication.domain.models.Notification
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class NotificationViewModel(val basePostgrestManager: BasePostgrestManager): ViewModel() {
    val isShow = MutableStateFlow(false)

    val userId = MutableStateFlow("")
    val listOfNotification = MutableStateFlow(listOf<Notification>())

    fun userId(email: String) = viewModelScope.launch{
        userId.update {
            basePostgrestManager.getUserId(email = email)
        }
        Log.d("userIdishka", userId.value)
    }

    fun getList(userId: String) = viewModelScope.launch {
        isShow.update {
            true
        }
        listOfNotification.update {
            basePostgrestManager.getNotification(userId)
        }


        isShow.update {
            false
        }
    }

}