package com.example.myapplication.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Order (
    val id:String?,
    val user_id:String?,
    val address:String?,
    val country:String?,
    val phone:String?,
    val email:String?,
    val payment_method:String?,
    val payed:Boolean?,
    val total_amount: Int?,
    val created_at:String?
)