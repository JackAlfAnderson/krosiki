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
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myapplication.data.app.App
import com.example.myapplication.data.supabase.Profile
import com.example.myapplication.presentation.categoryScreen.CategoryScreen
import com.example.myapplication.presentation.categoryScreen.vm.CategoryViewModel
import com.example.myapplication.presentation.checkoutScreen.CheckoutScreen
import com.example.myapplication.presentation.editProfile.EditProfileScreen
import com.example.myapplication.presentation.editProfile.vm.EditProfileViewModel
import com.example.myapplication.presentation.forgotPassword.ForgotPasswordScreen
import com.example.myapplication.presentation.forgotPassword.VM.ForgotPasswordViewModel
import com.example.myapplication.presentation.home.CategoryItem
import com.example.myapplication.presentation.home.HomeScreen
import com.example.myapplication.presentation.home.vm.HomeViewModel
import com.example.myapplication.presentation.likedScreen.LikedScreen
import com.example.myapplication.presentation.mapScreen.MapScreen
import com.example.myapplication.presentation.newPassword.NewPasswordScreen
import com.example.myapplication.presentation.newPassword.vm.NewPasswordViewModel
import com.example.myapplication.presentation.otpVerification.OtpVerificationScreen
import com.example.myapplication.presentation.otpVerification.vm.OtpVerificationViewModel
import com.example.myapplication.presentation.pagerScreen.PagerScreen
import com.example.myapplication.presentation.profile.ProfileScreen
import com.example.myapplication.presentation.profile.vm.ProfileViewModel
import com.example.myapplication.presentation.sideMenu.SideMenuScreen
import com.example.myapplication.presentation.sideMenu.vm.SideMenuViewModel
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
                val categories = listOf(
                    CategoryItem("Все",
                        containerColor = Color.White
                    ),
                    CategoryItem("Outdoor",
                        containerColor = Color.White
                    ),
                    CategoryItem("Tennis",
                        containerColor = Color.White
                    ),
                    CategoryItem("Running",
                        containerColor = Color.White
                    )
                )

                val navController = rememberNavController()
                val baseAuthManager = App.instance.baseAuthManager
                val basePostgrestManager = App.instance.basePostgrestManager
                val signUpViewModel = SignUpViewModel(baseAuthManager)
                val signInViewModel = SignInViewModel(baseAuthManager)
                val forgotPasswordViewModel = ForgotPasswordViewModel(baseAuthManager)
                val otpVerificationViewModel = OtpVerificationViewModel(baseAuthManager)
                val newPasswordViewModel = NewPasswordViewModel(baseAuthManager)
                val homeViewModel = HomeViewModel(basePostgrestManager)
                val categoryViewModel = CategoryViewModel(basePostgrestManager)
                val sideMenuViewModel = SideMenuViewModel(baseAuthManager)
                val profileViewModel = ProfileViewModel(baseAuthManager)
                val editProfileViewModel = EditProfileViewModel(baseAuthManager)

                var whichScreen by remember { mutableStateOf(0) }
                Scaffold(
                    bottomBar = {
                        if (whichScreen == 1 || whichScreen == 2 || whichScreen == 12){
                            BottomBar(whichScreen, navController)
                        }

                    }
                ) {
                    NavHost(
                        modifier = Modifier.padding(it),
                        navController = navController,
                        startDestination = "profile"
                    ) {
                        composable(route = "splash") {
                            whichScreen = 3
                            SplashScreen(navController)
                        }
                        composable(route = "pager") {
                            whichScreen = 10

                            PagerScreen(navController)
                        }
                        composable(route = "home") {
                            whichScreen = 1

                            HomeScreen(navController, homeViewModel, categories)
                        }
                        composable(route = "sideMenu") {
                            SideMenuScreen()
                        }
                        composable(route = "editProfile") {
                            EditProfileScreen(editProfileViewModel, navController)
                        }
                        composable(route = "profile") {
                            whichScreen = 12
                            ProfileScreen(profileViewModel, navController)
                        }
                        composable(route = "liked") {
                            whichScreen = 2
                            LikedScreen()
                        }
                        composable(
                            route = "Category/{category}",
                            arguments = listOf(
                                navArgument("category") {
                                    type = NavType.StringType
                                }
                            )
                        ) { backStackEntry ->
                            whichScreen = 11

                            CategoryScreen(
                                categories,
                                category = backStackEntry.arguments?.getString("category").toString(),
                                categoryViewModel = categoryViewModel
                            )
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
fun BottomBar(whichScreen: Int, navController: NavController) {
    //BottomNavigationIcons
    val bottomNavigationIcons1 = listOf(
        BottomNavigationItem(
            R.drawable.homeicon,
            tint = if (whichScreen == 1) Color(0xFF48B2E7) else Color.Unspecified,
            onClick = {
                navController.navigate("home")
            }
        ),
        BottomNavigationItem(
            R.drawable.favoriteicon,
            tint = if (whichScreen == 2) Color(0xFF48B2E7) else Color.Unspecified,
            onClick = {
                navController.navigate("liked")
            }
        )
    )
    val bottomNavigationIcons2 = listOf(
        BottomNavigationItem(
            R.drawable.notificationicon,
            tint = if (whichScreen == 3) Color(0xFF48B2E7) else Color.Unspecified,
            onClick = {

            }
        ),
        BottomNavigationItem(
            R.drawable.profileicon,
            tint = if (whichScreen == 4) Color(0xFF48B2E7) else Color.Unspecified,
            onClick = {

            }
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
                bottomNavigationItem.onClick()
            },
        tint = bottomNavigationItem.tint
    )
}

data class BottomNavigationItem(
    val icon: Int,
    val tint: Color,
    val onClick: () -> Unit
)