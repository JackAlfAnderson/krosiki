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
import com.example.myapplication.presentation.home.HomeScreen
import com.example.myapplication.presentation.mapScreen.MapScreen
import com.example.myapplication.presentation.otpVerification.OtpVerificationScreen
import com.example.myapplication.presentation.pagerScreen.PagerScreen
import com.example.myapplication.presentation.signIn.SignInScreen
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
                val signUpViewModel = SignUpViewModel(baseAuthManager = App.instance.baseAuthManager)
                NavHost(
                    navController = navController,
                    startDestination = "checkout"
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
                        SignInScreen(navController)
                    }
                    composable("otpVer") {
                        OtpVerificationScreen(navController)
                    }
                    composable(route = "signUp") {
                        SignUpScreen(navController, signUpViewModel)
                    }
                    composable("forgot") {
                        ForgotPasswordScreen(navController)
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

