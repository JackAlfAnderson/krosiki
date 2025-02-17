package com.example.myapplication.presentation.home.vm

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.supabase.BasePostgrestManager
import com.example.myapplication.domain.models.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(val basePostgrestManager: BasePostgrestManager): ViewModel() {

    val isShow = MutableStateFlow(false)
    val listOfProducts = MutableStateFlow<List<Product>>(listOf())
    val userId = MutableStateFlow("")

    fun getList() = viewModelScope.launch {
        try {
            isShow.update {
                true
            }
            val prod = basePostgrestManager.getProducts()
            Log.d("Products", prod.toString())
            listOfProducts.update {
                prod
            }
            isShow.update {
                false
            }
        } catch (e: Exception){
            Log.d("haha", "hahmachdati")
        }


    }
    fun getUserId(email: String) = viewModelScope.launch{
        userId.update {
            basePostgrestManager.getProfile(email).id.toString()
        }
        Log.d("userId", userId.toString())
    }
}