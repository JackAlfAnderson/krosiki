package com.example.myapplication.presentation.detailsScreen.vm

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.supabase.BasePostgrestManager
import com.example.myapplication.domain.models.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailsScreenViewModel(private val basePostgrestManager: BasePostgrestManager) : ViewModel() {
    val listOfProducts = MutableStateFlow(listOf<Product>())

    fun getList() = viewModelScope.launch{
        val lisOfProd = basePostgrestManager.getProducts()
        listOfProducts.update {
            lisOfProd
        }
    }
}