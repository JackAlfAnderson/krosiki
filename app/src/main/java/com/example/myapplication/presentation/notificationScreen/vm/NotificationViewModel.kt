package com.example.myapplication.presentation.notificationScreen.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.supabase.BasePostgrestManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class NotificationViewModel(val basePostgrestManager: BasePostgrestManager): ViewModel() {
    val isShow = MutableStateFlow(false)

    fun getList() = viewModelScope.launch {
        isShow.update {
            true
        }

        isShow.update {
            false
        }
    }
}