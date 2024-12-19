package com.example.myapplication.presentation.categoryScreen.vm

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.supabase.BasePostgrestManager
import com.example.myapplication.data.supabase.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CategoryViewModel(val basePostgrestManager: BasePostgrestManager) : ViewModel() {

    val listOfProducts = MutableStateFlow<List<Product>>(listOf())
    val isShow = MutableStateFlow(false)

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

}