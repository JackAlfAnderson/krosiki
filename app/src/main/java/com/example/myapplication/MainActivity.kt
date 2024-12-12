package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.data.app.App
import com.example.myapplication.presentation.checkoutScreen.CheckoutScreen
import com.example.myapplication.presentation.forgotPassword.ForgotPasswordScreen
import com.example.myapplication.presentation.forgotPassword.VM.ForgotPasswordViewModel
import com.example.myapplication.presentation.home.HomeScreen
import com.example.myapplication.presentation.mapScreen.MapScreen
import com.example.myapplication.presentation.newPassword.NewPasswordScreen
import com.example.myapplication.presentation.newPassword.vm.NewPasswordViewModel
import com.example.myapplication.presentation.otpVerification.OtpVerificationScreen
import com.example.myapplication.presentation.otpVerification.vm.OtpVerificationViewModel
import com.example.myapplication.presentation.pagerScreen.PagerScreen
import com.example.myapplication.presentation.signIn.SignInScreen
import com.example.myapplication.presentation.signIn.vm.SignInViewModel
import com.example.myapplication.presentation.signUp.SignUpScreen
import com.example.myapplication.presentation.signUp.vM.SignUpViewModel
import com.example.myapplication.presentation.splash.SplashScreen
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                val navController = rememberNavController()
                val baseAuthManager = App.instance.baseAuthManager
                val signUpViewModel = SignUpViewModel(baseAuthManager)
                val signInViewModel = SignInViewModel(baseAuthManager)
                val forgotPasswordViewModel = ForgotPasswordViewModel(baseAuthManager)
                val otpVerificationViewModel = OtpVerificationViewModel(baseAuthManager)
                val newPasswordViewModel = NewPasswordViewModel(baseAuthManager)
                NavHost(
                    navController = navController,
                    startDestination = "signIn"
                ){
                    composable(route = "splash"){
                        SplashScreen(navController)
                    }
                    composable(route = "pager") {
                        PagerScreen(navController)
                    }
                    composable(route = "home") {
                        HomeScreen()
                    }
                    composable(route = "signIn"){
                        SignInScreen(navController, signInViewModel)
                    }
                    composable("otpVer") {
                        OtpVerificationScreen(navController, otpVerificationViewModel, newPasswordViewModel)
                    }
                    composable(route = "signUp") {
                        SignUpScreen(navController, signUpViewModel)
                    }
                    composable("forgot") {
                        ForgotPasswordScreen(navController, forgotPasswordViewModel)
                    }
                    composable(route = "checkout") {
                        CheckoutScreen(navController)
                    }
                    composable(route = "map") {
                        MapScreen()
                    }

                }
            }
        }
    }
}

