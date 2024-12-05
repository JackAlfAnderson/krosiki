package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.presentation.home.HomeScreen
import com.example.myapplication.presentation.otpVerification.OtpVerificationScreen
import com.example.myapplication.presentation.pagerScreen.PagerScreen
import com.example.myapplication.presentation.signIn.SignInScreen
import com.example.myapplication.presentation.signUp.SignUpScreen
import com.example.myapplication.presentation.splash.SplashScreen
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "otpVer"
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
                        SignUpScreen(navController)
                    }
                    composable("forgot") {

                    }
                }
            }
        }
    }
}

