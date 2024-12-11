package com.example.myapplication.data.supabase

import android.location.Address
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.OtpType
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email
import io.github.jan.supabase.auth.providers.builtin.OTP
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

    suspend fun signIn(profile: Profile){
        supabaseClient.auth.signInWith(Email){
            email = profile.email
            password = profile.password
        }
    }

    suspend fun sendOtp(email: String){
        supabaseClient.auth.signInWith(OTP){
            this.email = email
        }
    }

    suspend fun verifyOTP(email: String, token: String){
        supabaseClient.auth.verifyEmailOtp(OtpType.Email.EMAIL, email = email, token = token)
    }

    suspend fun newPassword(email: String, password: String){
        supabaseClient.auth.updateUser {
            this.email = email
            this.password = password
        }
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