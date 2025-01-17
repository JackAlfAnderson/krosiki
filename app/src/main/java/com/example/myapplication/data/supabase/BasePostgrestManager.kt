package com.example.myapplication.data.supabase

import android.util.Log
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

class BasePostgrestManager(
    private val supabaseClient: SupabaseClient
) {
    suspend fun getProducts(): List<Product> {
        //Log.d("produtsasdfkahlkfw", "yesyes")
        val listOfProducts = supabaseClient.postgrest["products"].select().decodeList<Product>()
        //Log.d("produtsasdfkahlkfw", listOfProducts.toString())
        return listOfProducts
    }
}

@Serializable
data class Product(
    val id: String?,
    val name: String?,
    val category: String?,
    val description: String?,
    val price: Double?,
    val best_seller: Boolean?,
    val image: String?,
    val created_at: String?
)