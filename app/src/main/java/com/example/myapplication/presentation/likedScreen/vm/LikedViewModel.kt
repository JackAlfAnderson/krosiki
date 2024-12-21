package com.example.myapplication.presentation.likedScreen.vm

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.supabase.BasePostgrestManager
import com.example.myapplication.data.supabase.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LikedViewModel(val basePostgrestManager: BasePostgrestManager) : ViewModel() {

    val listOfProducts = MutableStateFlow(listOf<Product>())
    val isShow = MutableStateFlow(false)

    fun getList() = viewModelScope.launch{
        try {
            isShow.update {
                true
            }
            val listOf = basePostgrestManager.getProducts()

            listOfProducts.update {
                listOf
            }
            isShow.update {
                false
            }

        } catch (e: Exception){
            Log.d("likedViewModelError", e.message.toString())
        }
    }

}