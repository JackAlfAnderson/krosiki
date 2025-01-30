package com.example.myapplication.presentation.categoryScreen

import android.util.Log
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapplication.R
import com.example.myapplication.data.EmailManager
import com.example.myapplication.data.app.App
import com.example.myapplication.domain.models.Product
import com.example.myapplication.presentation.categoryScreen.vm.CategoryViewModel
import com.example.myapplication.presentation.home.CategoryItem
import com.example.myapplication.presentation.home.SneakersScreen
import com.example.myapplication.presentation.myCart.Vm.MyCartViewModel

@Composable
fun CategoryScreen(
    category: String,
    categoryViewModel: CategoryViewModel,
    navController: NavController,
    myCartViewModel: MyCartViewModel
) {

    val email = EmailManager(LocalContext.current).get()
    myCartViewModel.userId(email)
    val userId by myCartViewModel.userId.collectAsState()

    categoryViewModel.getList()

    val isShow by categoryViewModel.isShow.collectAsState()

    var isChoosen by remember { mutableStateOf(false) }
    var isChoosen2 by remember { mutableStateOf(false) }
    var isChoosen3 by remember { mutableStateOf(false) }
    var isChoosen4 by remember { mutableStateOf(false) }

    var shoes by remember { mutableStateOf(listOf<Product>()) }

    var whatCategory by remember { mutableStateOf(category) }

    val sneakers by categoryViewModel.listOfProducts.collectAsState()
    
    Log.d("AppCheck", App.listProducts.toString())

    LaunchedEffect(Unit) {

        when(category){
            "Все" -> isChoosen = true
            "Outdoor" -> isChoosen2 = true
            "Tennis" -> isChoosen3 = true
            "Running" -> isChoosen4 = true

        }
        shoes = filterSneakers(sneakers, whatCategory)

    }


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
           Column(

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
                    whatCategory,
                    fontSize = 16.sp,
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
                Spacer(Modifier.height(22.dp))
                Text(
                    text = "Категории", fontSize = 16.sp
                )
                Spacer(Modifier.height(19.dp))
               LazyRow {
                   item {
                       val animateButtonColor by animateColorAsState(
                           if (isChoosen) Color(0xFF48B2E7) else Color.White,
                           tween(500)
                       )
                       val animateTextColor by animateColorAsState(
                           if (isChoosen) Color.White else Color.Black,
                           tween(500)
                       )

                       Column(Modifier.padding(horizontal = 8.dp)) {
                           Button(
                               onClick = {
                                        isChoosen = true
                                       isChoosen2 = false
                                       isChoosen3 = false
                                       isChoosen4 = false
                                       whatCategory = "Все"
                                   shoes = sneakers

                               },
                               shape = RoundedCornerShape(8.dp),
                               colors = ButtonDefaults.buttonColors(
                                   containerColor = animateButtonColor
                               ),
                               modifier = Modifier.size(height = 40.dp, width = 108.dp).padding()
                           ) {
                               Text("Все", color = animateTextColor)
                           }
                       }
                   }
                   item {
                       val animateButtonColor by animateColorAsState(
                           if (isChoosen2) Color(0xFF48B2E7) else Color.White,
                           tween(500)
                       )
                       val animateTextColor by animateColorAsState(
                           if (isChoosen2) Color.White else Color.Black,
                           tween(500)
                       )

                       Column(Modifier.padding(horizontal = 8.dp)) {
                           Button(
                               onClick = {

                                       isChoosen = false
                                       isChoosen3 = false
                                       isChoosen4 = false
                                       isChoosen2 = true
                                       whatCategory = "Outdoor"
                                   shoes = filterSneakers(sneakers, whatCategory)


                               },
                               shape = RoundedCornerShape(8.dp),
                               colors = ButtonDefaults.buttonColors(
                                   containerColor = animateButtonColor
                               ),
                               modifier = Modifier.size(height = 40.dp, width = 108.dp).padding()
                           ) {
                               Text("Outdoor", color = animateTextColor)
                           }
                       }
                   }
                   item {
                       val animateButtonColor by animateColorAsState(
                           if (isChoosen3) Color(0xFF48B2E7) else Color.White,
                           tween(500)
                       )
                       val animateTextColor by animateColorAsState(
                           if (isChoosen3) Color.White else Color.Black,
                           tween(500)
                       )

                       Column(Modifier.padding(horizontal = 8.dp)) {
                           Button(
                               onClick = {

                                       isChoosen2 = false
                                       isChoosen = false
                                       isChoosen4 = false
                                       isChoosen3 = true
                                       whatCategory = "Tennis"
                                   shoes = filterSneakers(sneakers, whatCategory)


                               },
                               shape = RoundedCornerShape(8.dp),
                               colors = ButtonDefaults.buttonColors(
                                   containerColor = animateButtonColor
                               ),
                               modifier = Modifier.size(height = 40.dp, width = 108.dp).padding()
                           ) {
                               Text("Tennis", color = animateTextColor)
                           }
                       }
                   }
                   item {
                       val animateButtonColor by animateColorAsState(
                           if (isChoosen4) Color(0xFF48B2E7) else Color.White,
                           tween(500)
                       )
                       val animateTextColor by animateColorAsState(
                           if (isChoosen4) Color.White else Color.Black,
                           tween(500)
                       )

                       Column(Modifier.padding(horizontal = 8.dp)) {
                           Button(
                               onClick = {

                                       isChoosen2 = false
                                       isChoosen3 = false
                                       isChoosen = false
                                       isChoosen4 = true
                                       whatCategory = "Running"
                                   shoes = filterSneakers(sneakers, whatCategory)


                               },
                               shape = RoundedCornerShape(8.dp),
                               colors = ButtonDefaults.buttonColors(
                                   containerColor = animateButtonColor
                               ),
                               modifier = Modifier.size(height = 40.dp, width = 108.dp).padding()
                           ) {
                               Text("Running", color = animateTextColor)
                           }
                       }
                   }
               }

                Spacer(Modifier.height(25.dp))

               LazyVerticalGrid(
                   columns = GridCells.Fixed(2)
               ) {
                   items(shoes){ item ->
                       SneakersScreen(item, navController, myCartViewModel, userId)
                   }
               }


            }
            Spacer(Modifier.height(20.dp))

        }
    }
}

fun filterSneakers(sneakers: List<Product>, whatCategory: String) : List<Product>{

    var shoes = mutableListOf<Product>()

    sneakers.forEach {
        if (it.category == whatCategory){
            shoes.add(it)
        }
    }

    return shoes
}