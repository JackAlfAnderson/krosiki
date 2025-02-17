package com.example.myapplication.data.app

import android.app.Application
import android.net.Uri
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.myapplication.data.supabase.BaseAuthManager
import com.example.myapplication.data.supabase.BasePostgrestManager
import com.example.myapplication.domain.models.Product
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.storage.Storage

class App(): Application() {
    companion object {
        lateinit var instance : App
            private set
        var listProducts: MutableList<Product> = mutableListOf()
        var listOfSearch: MutableList<String> = mutableListOf()
        var userId: String = ""
        var selectedImageUri: Uri? = null
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

    }


    private val supabaseClient by lazy {
        createSupabaseClient(
            supabaseUrl = "https://jxdsyxjacclupdsirxed.supabase.co",
            supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Imp4ZHN5eGphY2NsdXBkc2lyeGVkIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MzM0NzM3MDQsImV4cCI6MjA0OTA0OTcwNH0.-6d1GMiZkMyaUkRMWk0EfZLxeXKGKEa-UNarFphKXVo"
        ){
            install(Postgrest)
            install(Auth)
            install(Storage)
        }
    }

    val baseAuthManager by lazy {
        BaseAuthManager(supabaseClient)
    }

    val basePostgrestManager by lazy {
        BasePostgrestManager(supabaseClient)
    }
}
