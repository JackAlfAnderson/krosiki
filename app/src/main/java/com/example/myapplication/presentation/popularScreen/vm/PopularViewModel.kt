package com.example.myapplication.presentation.popularScreen.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.supabase.BasePostgrestManager
import com.example.myapplication.data.supabase.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PopularViewModel(val basePostgrestManager: BasePostgrestManager):ViewModel() {

    val listOfProducts = MutableStateFlow(listOf<Product>())
    val isShow = MutableStateFlow(false)

    fun getList() = viewModelScope.launch {
        try {
            isShow.update {
                true
            }

            val prod = basePostgrestManager.getProducts()

            listOfProducts.update {
                prod
            }

            isShow.update {
                false
            }

        }catch (e:Exception){

        }
    }

}