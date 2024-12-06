package com.example.myapplication.data.supabase

import android.location.Address
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.serialization.Serializable

class BaseAuthManager(
    private val supabaseClient: SupabaseClient
) {
    suspend fun signUp(profile: Profile){
        supabaseClient.auth.signUpWith(Email){
            email = profile.email
            password = profile.password
        }
        supabaseClient.postgrest["profiles"].insert(profile)
    }


}

@Serializable
data class Profile(
    val id: Int? = null,
    val name: String = "",
    val surname: String? = null,
    val address: String? = null,
    val phone: String? = null,
    val email:String = "",
    val created_at: String? = null,
    val password:String = ""
)