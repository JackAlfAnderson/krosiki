package com.example.myapplication.data.supabase


import android.content.Context
import android.net.Uri
import android.util.Log
import com.example.myapplication.domain.models.Cart
import com.example.myapplication.domain.models.Favorite
import com.example.myapplication.domain.models.Notification
import com.example.myapplication.domain.models.Order
import com.example.myapplication.domain.models.OrderItem
import com.example.myapplication.domain.models.Product
import com.example.myapplication.domain.models.Profile
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.storage.storage
import java.io.File
import java.io.InputStream

class BasePostgrestManager(
    private val supabaseClient: SupabaseClient
) {

    suspend fun uploadImage(userId: String, imageUri: Uri, context: Context) {
        val file = File(imageUri.path) // Преобразуй URI в файл
        val bucket = supabaseClient.storage["images"] // Выбери bucket
        // Загрузи файл в Storage
        val filePath = "user_$userId/${file.name}" // Путь к файлу, связанный с ID пользователя
        val fileByteArray = uriToByteArray(context = context, imageUri)
        bucket.upload(filePath, fileByteArray)
    }

    suspend fun getUserImageUrl(userId: String): String {
        val bucket = supabaseClient.storage["images"]
        val filePath = "user_$userId/profile.jpg" // Путь к файлу: папка пользователя + фиксированное имя файла
        return bucket.publicUrl(filePath) // Получи публичную ссылку на картинку
    }

    fun uriToByteArray(context: Context, uri: Uri): ByteArray {
        val inputStream: InputStream? = context.contentResolver.openInputStream(uri)
        return inputStream?.readBytes() ?: throw IllegalArgumentException("Не удалось открыть InputStream для Uri: $uri")
    }

    suspend fun getProducts(): List<Product> {

        val listOfProducts = supabaseClient.postgrest["products"].select().decodeList<Product>()

        return listOfProducts
    }

    suspend fun getCartItems(userId: String): List<Product> {

        val listOfCart = supabaseClient.postgrest["cart"].select {
            filter {
                eq("user_id", userId)
            }
        }.decodeList<Cart>()

        val listOfCartItems = mutableListOf<Product>()

        listOfCart.forEach {
            listOfCartItems.add(
                supabaseClient.postgrest["products"].select {
                    filter {
                        eq("id", it.product_id!!)
                    }
                }.decodeSingle<Product>()
            )
        }

        return listOfCartItems
    }

    suspend fun getNotification(userId: String): List<Notification> {

        val listOfNotification = supabaseClient.postgrest["notifications"].select {
            filter {
                eq("user_id", userId)
            }
        }.decodeList<Notification>()

        return listOfNotification
    }

    suspend fun getProfile(email: String): Profile {
        val userProfile = supabaseClient.postgrest["profiles"].select {
            filter {
                eq("email", email)
            }
        }.decodeSingle<Profile>()
        return userProfile
    }

    suspend fun getUserId(email: String): String {
        val profile = supabaseClient.postgrest["profiles"].select {
            filter {
                eq("email", email)
            }
        }.decodeSingle<Profile>()
        val userId = profile.id

        return userId.toString()
    }

    suspend fun addCartItem(cart: Cart) {
        supabaseClient.postgrest["cart"].insert(cart)
    }

    suspend fun getProductById(productId: String) : Product{
        val product = supabaseClient.postgrest["products"].select {
            filter {
                eq("id", productId)
            }
        }.decodeSingle<Product>()

        return product
    }
    suspend fun getOrders(userId: String):List<Order>{
        val listOfOrders = supabaseClient.postgrest["orders"].select {
            filter {
                eq("user_id", userId)
            }
        }.decodeList<Order>()

        return listOfOrders
    }

    suspend fun getOrderItems(userId: String): List<OrderItem> {
        val listOfOrders = supabaseClient.postgrest["orders"].select {
            filter {
                eq("user_id", userId)
            }
        }.decodeList<Order>()

        val listOfOrderItems = mutableListOf<OrderItem>()

        listOfOrders.forEach {
            listOfOrderItems.add(
                supabaseClient.postgrest["order_items"].select {
                    filter {
                        eq("order_id", it.id!!)
                    }
                }.decodeSingle<OrderItem>()
            )
        }

        return listOfOrderItems
    }

    suspend fun getProdFromOrderItems(userId: String) : List<Product>{
        val listOfOrderItems = getOrderItems(userId)

        val listOfProducts = mutableListOf<Product>()

        listOfOrderItems.forEach {
            listOfProducts.add(
                supabaseClient.postgrest["products"].select {
                    filter {
                        eq("id", it.product_id!!)
                    }
                }.decodeSingle<Product>()
            )
        }

        return listOfProducts
    }

}
