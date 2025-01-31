package com.example.myapplication.presentation.orders.VM

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.supabase.BasePostgrestManager
import com.example.myapplication.domain.models.Order
import com.example.myapplication.domain.models.OrderItem
import com.example.myapplication.domain.models.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class OrdersScreenViewModel(val basePostgrestManager: BasePostgrestManager) : ViewModel() {

    val listOfOrders = MutableStateFlow(listOf<Order>())
    val listOfOrderItems = MutableStateFlow(listOf<OrderItem>())
    val listOfProducts = MutableStateFlow(listOf<Product>())
    val userId = MutableStateFlow("")
    val product = MutableStateFlow(Product(
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null
        )
    )

    fun getOrders(userId:String) = viewModelScope.launch{
        listOfOrders.update {
            basePostgrestManager.getOrders(userId)
        }

    }

    fun getOrderItems(userId: String) = viewModelScope.launch {
        listOfOrderItems.update {
            basePostgrestManager.getOrderItems(userId)
        }
    }

    fun getProductsFromOrder(userId: String) = viewModelScope.launch {
        listOfProducts.update {
            basePostgrestManager.getProdFromOrderItems(userId)
        }
    }

    fun getProductById(productId:String) = viewModelScope.launch {
        product.update {
            basePostgrestManager.getProductById(productId)
        }
    }

    fun userId(email: String) = viewModelScope.launch{
        userId.update {
            basePostgrestManager.getUserId(email = email)
        }
        Log.d("userIdishka", userId.value)
    }


}