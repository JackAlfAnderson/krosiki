package com.example.myapplication.presentation.likedScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapplication.R
import com.example.myapplication.data.EmailManager
import com.example.myapplication.data.app.App
import com.example.myapplication.domain.models.Product
import com.example.myapplication.presentation.home.SneakersScreen
import com.example.myapplication.presentation.likedScreen.vm.LikedViewModel
import com.example.myapplication.presentation.myCart.Vm.MyCartViewModel

@Composable
fun LikedScreen(likedViewModel: LikedViewModel, navController: NavController, myCartViewModel: MyCartViewModel) {

    val email = EmailManager(LocalContext.current).get()

    likedViewModel.getList()


    val isShow by likedViewModel.isShow.collectAsState()
    var shoes by remember { mutableStateOf(listOf<Product>()) }

    val sneakers by likedViewModel.listOfProducts.collectAsState()

    shoes = sneakers

    Column(
        Modifier.background(Color(0xFFF7F7F9))
    ) {
        if (isShow) {
            CircularProgressIndicator(
                modifier = Modifier.size(1.dp)
            )
        }
        Column(
            Modifier
                .fillMaxSize()
                .background(Color(0xFFF7F7F9))
                .padding(20.dp)

        ) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                Box(contentAlignment = Alignment.CenterStart,
                    modifier = Modifier.fillMaxWidth()) {
                    Icon(
                        painterResource(R.drawable.back_icon),
                        null,
                        tint = Color.Unspecified,
                        modifier = Modifier.clickable {
                            navController.navigate("home")
                        }
                    )
                }
                Text(
                    "Избранное",
                    fontSize = 16.sp,
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
                Box(
                    contentAlignment = Alignment.CenterEnd,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Card(
                        shape = RoundedCornerShape(40.dp),
                        modifier = Modifier.size(44.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White
                        )
                    ) {
                        Column(
                            Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.fillheart),
                                null,
                                tint = Color.Unspecified,
                                modifier = Modifier.size(24.dp)
                            )
                        }

                    }

                }

            }
            Spacer(Modifier.height(20.dp))
            LazyVerticalGrid(
                columns = GridCells.Fixed(2)
            ) {
                items(shoes){ item ->
                    SneakersScreen(item, navController, myCartViewModel, App.userId)
                }
            }
        }
    }

}