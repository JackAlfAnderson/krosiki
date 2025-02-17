package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myapplication.data.app.App
import com.example.myapplication.presentation.categoryScreen.CategoryScreen
import com.example.myapplication.presentation.categoryScreen.vm.CategoryViewModel
import com.example.myapplication.presentation.checkoutScreen.CheckoutScreen
import com.example.myapplication.presentation.detailsScreen.DetailsScreen
import com.example.myapplication.presentation.detailsScreen.vm.DetailsScreenViewModel
import com.example.myapplication.presentation.editProfile.EditProfileScreen
import com.example.myapplication.presentation.editProfile.vm.EditProfileViewModel
import com.example.myapplication.presentation.forgotPassword.ForgotPasswordScreen
import com.example.myapplication.presentation.forgotPassword.VM.ForgotPasswordViewModel
import com.example.myapplication.presentation.home.CategoryItem
import com.example.myapplication.presentation.home.HomeScreen
import com.example.myapplication.presentation.home.vm.HomeViewModel
import com.example.myapplication.presentation.likedScreen.LikedScreen
import com.example.myapplication.presentation.likedScreen.vm.LikedViewModel
import com.example.myapplication.presentation.mapScreen.MapScreen
import com.example.myapplication.presentation.myCart.MyCart
import com.example.myapplication.presentation.myCart.Vm.MyCartViewModel
import com.example.myapplication.presentation.newPassword.vm.NewPasswordViewModel
import com.example.myapplication.presentation.notificationScreen.NotificationScreen
import com.example.myapplication.presentation.notificationScreen.vm.NotificationViewModel
import com.example.myapplication.presentation.orders.OrdersScreen
import com.example.myapplication.presentation.orders.VM.OrdersScreenViewModel
import com.example.myapplication.presentation.otpVerification.OtpVerificationScreen
import com.example.myapplication.presentation.otpVerification.vm.OtpVerificationViewModel
import com.example.myapplication.presentation.pagerScreen.PagerScreen
import com.example.myapplication.presentation.popularScreen.PopularScreen
import com.example.myapplication.presentation.popularScreen.vm.PopularViewModel
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

    val baseAuthManager = App.instance.baseAuthManager
    val basePostgrestManager = App.instance.basePostgrestManager
    val signUpViewModel = SignUpViewModel(baseAuthManager)
    val signInViewModel = SignInViewModel(baseAuthManager)
    val forgotPasswordViewModel = ForgotPasswordViewModel(baseAuthManager)
    val otpVerificationViewModel = OtpVerificationViewModel(baseAuthManager)
    val newPasswordViewModel = NewPasswordViewModel(baseAuthManager)
    val homeViewModel = HomeViewModel(basePostgrestManager)
    val categoryViewModel = CategoryViewModel(basePostgrestManager)
    val sideMenuViewModel = SideMenuViewModel(baseAuthManager, basePostgrestManager)
    val profileViewModel = ProfileViewModel(baseAuthManager, basePostgrestManager)
    val editProfileViewModel = EditProfileViewModel(baseAuthManager, basePostgrestManager)
    val likedViewModel = LikedViewModel(basePostgrestManager)
    val popularViewModel = PopularViewModel(basePostgrestManager)
    val notificationViewModel = NotificationViewModel(basePostgrestManager)
    val detailsViewModel = DetailsScreenViewModel(basePostgrestManager)
    val myCartViewModel = MyCartViewModel(basePostgrestManager)
    val orderScreenViewModel = OrdersScreenViewModel(basePostgrestManager)


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

                var whichScreen by remember { mutableStateOf(0) }
                Scaffold(
                    bottomBar = {
                        if (whichScreen == 3 || whichScreen == 7 || whichScreen == 6 || whichScreen == 5 || whichScreen == 15){
                            BottomBar(whichScreen, navController)
                        }//home liked profile editProfile notification

                    }
                ) {
                    NavHost(
                        modifier = Modifier.padding(it),
                        navController = navController,
                        startDestination = "signIn"
                    ) {
                        composable(route = "splash") {
                            whichScreen = 1
                            SplashScreen(navController)
                        }
                        composable(route = "pager") {
                            whichScreen = 2

                            PagerScreen(navController)
                        }
                        composable(route = "home") {
                            whichScreen = 3

                            HomeScreen(navController, homeViewModel, categories, myCartViewModel)
                        }
                        composable(route = "sideMenu") {
                            whichScreen = 4
                            SideMenuScreen(navController, sideMenuViewModel)
                        }
                        composable(route = "editProfile") {
                            whichScreen = 5
                            EditProfileScreen(editProfileViewModel, navController)
                        }
                        composable(route = "profile") {
                            whichScreen = 6
                            ProfileScreen(profileViewModel, navController)
                        }
                        composable(route = "liked") {
                            whichScreen = 7
                            LikedScreen(likedViewModel, navController, myCartViewModel)
                        }
                        composable(
                            route = "Category/{category}",
                            arguments = listOf(
                                navArgument("category") {
                                    type = NavType.StringType
                                }
                            )
                        ) { backStackEntry ->
                            whichScreen = 8

                            CategoryScreen(
                                category = backStackEntry.arguments?.getString("category").toString(),
                                categoryViewModel = categoryViewModel,
                                navController, myCartViewModel
                            )
                        }
                        composable(route = "signIn") {
                            whichScreen = 9

                            SignInScreen(navController, signInViewModel)
                        }
                        composable("otpVer") {
                            whichScreen = 10
                            OtpVerificationScreen(
                                navController,
                                otpVerificationViewModel,
                                newPasswordViewModel
                            )
                        }
                        composable(route = "signUp") {
                            whichScreen = 11
                            SignUpScreen(navController, signUpViewModel)
                        }
                        composable("forgot") {
                            whichScreen = 12
                            ForgotPasswordScreen(navController, forgotPasswordViewModel)
                        }
                        composable(route = "checkout") {
                            whichScreen = 13
                            CheckoutScreen(navController)
                        }
                        composable(route = "map") {
                            whichScreen = 14
                            MapScreen()
                        }
                        composable(route = "notification") {
                            whichScreen = 15
                            NotificationScreen(notificationViewModel, navController)
                        }
                        composable(route = "popular") {
                            whichScreen = 16
                            PopularScreen(popularViewModel, navController, myCartViewModel)
                        }
                        composable(route = "details") {
                            whichScreen = 17
                            DetailsScreen(navController, detailsViewModel)
                        }
                        composable(route = "cart") {
                            whichScreen = 18
                            MyCart(myCartViewModel, navController)
                        }
                        composable(route = "orders") {
                            whichScreen = 19
                            OrdersScreen(orderScreenViewModel, navController)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun BottomBar(whichScreen: Int, navController: NavController) {
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
            modifier = Modifier.padding(bottom = 20.dp).clickable {
                navController.navigate("cart")
            }
        )
        Box {
            Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterStart) {
                Row(Modifier.padding(top = 40.dp)) {
                    BottomNavigationItemScreen(
                        BottomNavigationItem(
                            R.drawable.homeicon,
                            tint = if (whichScreen == 3) Color(0xFF48B2E7) else Color.Unspecified,
                            onClick = {
                                navController.navigate("home")
                            }
                        )
                    )
                    BottomNavigationItemScreen(
                        BottomNavigationItem(
                            R.drawable.favoriteicon,
                            tint = if (whichScreen == 7) Color(0xFF48B2E7) else Color.Unspecified,
                            onClick = {
                                navController.navigate("liked")
                            }
                        )
                    )
                }
            }
            Box (Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd){
                Row(Modifier.padding(top = 40.dp)) {
                    BottomNavigationItemScreen(
                        BottomNavigationItem(
                            R.drawable.notificationicon,
                            tint = if (whichScreen == 15) Color(0xFF48B2E7) else Color.Unspecified,
                            onClick = {
                                navController.navigate("notification")
                            }
                        )
                    )
                    BottomNavigationItemScreen(
                        BottomNavigationItem(
                            R.drawable.profileicon,
                            tint = if (whichScreen == 6) Color(0xFF48B2E7) else Color.Unspecified,
                            onClick = {
                                navController.navigate("profile")
                            }
                        )
                    )
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