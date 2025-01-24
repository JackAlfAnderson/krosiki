package com.example.myapplication.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Notification (
    val id: String? = "",
    val user_id:String? = "",
    val title: String,
    val description: String? = "",
    val notification_date:String? = "",
    val notification_time: String? = "",
    val created_at: String? = ""
)