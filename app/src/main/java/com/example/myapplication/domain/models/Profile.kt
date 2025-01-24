package com.example.myapplication.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Profile(
    val id: String? = null,
    val name: String = "",
    val surname: String? = null,
    val address: String? = null,
    val phone: String? = null,
    val email: String = "",
    val created_at: String? = null,
    val password: String = ""
)