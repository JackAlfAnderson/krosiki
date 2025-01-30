package com.example.myapplication.presentation.myCart.Vm

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.supabase.BasePostgrestManager
import com.example.myapplication.domain.models.Cart
import com.example.myapplication.domain.models.Favorite
import com.example.myapplication.domain.models.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MyCartViewModel(val basePostgrestManager: BasePostgrestManager) : ViewModel() {

    val userId = MutableStateFlow("")
    val listOfCartItems = MutableStateFlow(listOf<Product>())

    fun addCartItem(userId: String, productId: String, quantity:Int) = viewModelScope.launch{

        basePostgrestManager.addCartItem(Cart(user_id = userId, product_id = productId, quantity = quantity))
        Log.d("clicked", "выполнилось")

    }

    fun userId(email: String) = viewModelScope.launch{
        userId.update {
            basePostgrestManager.getUserId(email = email)
        }
        Log.d("userIdishka", userId.value)
    }

    fun getCartItemsList(userId: String) = viewModelScope.launch {

        val cartItems = basePostgrestManager.getCartItems(userId)

        listOfCartItems.update {
            cartItems
        }


    }
}