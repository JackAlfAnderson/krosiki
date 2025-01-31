package com.example.myapplication.presentation.orderDetails.Vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.supabase.BasePostgrestManager
import com.example.myapplication.domain.models.OrderItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class orderDetailsViewModel(val basePostgrestManager: BasePostgrestManager) : ViewModel() {

    val listOfOrderItems = MutableStateFlow(listOf<OrderItem>())

    fun getOrderItems(userId: String) = viewModelScope.launch {
        listOfOrderItems.update {
            basePostgrestManager.getOrderItems(userId)
        }
    }

    fun getProfile(email: String) = viewModelScope.launch {
        basePostgrestManager.getProfile(email)
    }

}