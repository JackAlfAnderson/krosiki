package com.example.myapplication.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class OrderItem(
    val id: String? = null,
    val order_id: String?,
    val product_id: String?,
    val quantity: Int,
    val price: Double,
    val created_at: String?
)