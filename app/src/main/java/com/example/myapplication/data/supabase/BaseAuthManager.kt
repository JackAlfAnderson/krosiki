package com.example.myapplication.data.supabase

import com.example.myapplication.domain.models.Profile
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
    suspend fun signUp(profile: Profile) {
        supabaseClient.auth.signUpWith(Email) {
            email = profile.email
            password = profile.password
        }
        supabaseClient.postgrest["profiles"].insert(profile)
    }

    suspend fun signIn(profile: Profile) {
        supabaseClient.auth.signInWith(Email) {
            email = profile.email
            password = profile.password
        }
    }

    suspend fun sendOtp(email: String) {
        supabaseClient.auth.signInWith(OTP) {
            this.email = email
        }
    }

    suspend fun verifyOTP(email: String, token: String) {
        supabaseClient.auth.verifyEmailOtp(OtpType.Email.EMAIL, email = email, token = token)
    }

    suspend fun newPassword(profile: Profile) {
        supabaseClient.auth.updateUser {
            email = profile.email
            password = profile.password
        }
        supabaseClient.postgrest["profiles"].update(
            {
                set("password", profile.password)
            }
        ) {
            filter {
                eq("email", profile.email)
            }
        }

    }

    suspend fun getProfile(email: String): Profile {
        val userProfile = supabaseClient.postgrest["profiles"].select {
            filter {
                eq("email", email)
            }
        }.decodeSingle<Profile>()
        return userProfile
    }

    suspend fun updateProfile(profile: Profile) {
        supabaseClient.postgrest["profiles"].update(
            {
                set("name", profile.name)
                set("surname", profile.surname)
                set("address", profile.address)
                set("phone", profile.phone)
            }

        ) {
            filter {
                eq("email", profile.email)
            }
        }

    }


}
