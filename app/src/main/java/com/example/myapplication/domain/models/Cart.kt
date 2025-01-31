package com.example.myapplication.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Cart(
    val id:Int? = null,
    val user_id:String,
    val product_id:String,
    val quantity:Int
)