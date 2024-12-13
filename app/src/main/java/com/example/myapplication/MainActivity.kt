package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
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
                var whichScreen by remember { mutableStateOf(0) }
                Scaffold(
                    bottomBar = {
                        if (whichScreen == 1 ){
                            BottomBar(whichScreen)
                        }

                    }
                ) {
                    NavHost(
                        modifier = Modifier.padding(it),
                        navController = navController,
                        startDestination = "signIn"
                    ) {
                        composable(route = "splash") {
                            whichScreen = 3
                            SplashScreen(navController)
                        }
                        composable(route = "pager") {
                            whichScreen = 2

                            PagerScreen(navController)
                        }
                        composable(route = "home") {
                            whichScreen = 1

                            HomeScreen()
                        }
                        composable(route = "signIn") {
                            whichScreen = 4

                            SignInScreen(navController, signInViewModel)
                        }
                        composable("otpVer") {
                            whichScreen = 5
                            OtpVerificationScreen(
                                navController,
                                otpVerificationViewModel,
                                newPasswordViewModel
                            )
                        }
                        composable(route = "signUp") {
                            whichScreen = 6
                            SignUpScreen(navController, signUpViewModel)
                        }
                        composable("forgot") {
                            whichScreen = 7
                            ForgotPasswordScreen(navController, forgotPasswordViewModel)
                        }
                        composable(route = "checkout") {
                            whichScreen = 8
                            CheckoutScreen(navController)
                        }
                        composable(route = "map") {
                            whichScreen = 9
                            MapScreen()
                        }

                    }
                }
            }
        }
    }
}

@Composable
fun BottomBar(whichScreen: Int) {
    //BottomNavigationIcons
    val bottomNavigationIcons1 = listOf(
        BottomNavigationItem(
            R.drawable.homeicon,
            tint = if (whichScreen == 1) Color(0xFF48B2E7) else Color.Unspecified
        ),
        BottomNavigationItem(
            R.drawable.favoriteicon,
            tint = if (whichScreen == 1) Color.Black else Color.Unspecified
        )
    )
    val bottomNavigationIcons2 = listOf(
        BottomNavigationItem(
            R.drawable.notificationicon,
            tint = if (whichScreen == 1) Color.Black else Color.Unspecified
        ),
        BottomNavigationItem(
            R.drawable.profileicon,
            tint = if (whichScreen == 1) Color.Black else Color.Unspecified
        )
    )

    Box (
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(R.drawable.bottomnavigation),
            null,
            modifier = Modifier
                .fillMaxWidth()
                .height(125.dp)
        )
        Icon(
            painter = painterResource(R.drawable.shopicon),
            null,
            tint = Color.Unspecified,
            modifier = Modifier.padding(bottom = 20.dp)
        )
        Row {
            LazyRow(Modifier.padding(top = 40.dp)) {
                items(bottomNavigationIcons1) { item ->
                    BottomNavigationItemScreen(item)
                }
            }
            Spacer(Modifier.width(130.dp))
            LazyRow(Modifier.padding(top = 40.dp)) {
                items(bottomNavigationIcons2) {item ->
                    BottomNavigationItemScreen(item)
                }
            }
        }

    }
}

@Composable
fun BottomNavigationItemScreen(bottomNavigationItem: BottomNavigationItem) {
    Icon(
        painter = painterResource(bottomNavigationItem.icon),
        null,
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .clickable {

            },
        tint = bottomNavigationItem.tint
    )
}

data class BottomNavigationItem(
    val icon: Int,
    val tint: Color
)