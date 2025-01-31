package com.example.myapplication.presentation.myCart

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import coil3.compose.AsyncImage
import com.example.myapplication.R
import com.example.myapplication.data.EmailManager
import com.example.myapplication.domain.models.Cart
import com.example.myapplication.domain.models.Product
import com.example.myapplication.presentation.myCart.Vm.MyCartViewModel

@Composable
fun MyCart(myCartViewModel: MyCartViewModel) {

    val email = EmailManager(LocalContext.current).get()

    val userId by myCartViewModel.userId.collectAsState()

    LaunchedEffect(Unit) {
        myCartViewModel.getCartItemsList(userId)
        myCartViewModel.userId(email)
    }




    val listOfCartItems by myCartViewModel.listOfCartItems.collectAsState()

    Box (){
        Column(
            Modifier
                .fillMaxSize()
                .padding(20.dp),
        ) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                Box(
                    contentAlignment = Alignment.CenterStart,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        painterResource(R.drawable.back_icon),
                        null,
                        tint = Color.Unspecified,
                        modifier = Modifier.clickable {

                        }
                    )
                }
                Text(
                    "Корзина",
                    fontSize = 16.sp,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )


            }
            Spacer(Modifier.height(16.dp))
            Text("3 товара", fontSize = 16.sp)
            LazyColumn {
                items(listOfCartItems){ item ->
                    SneakerCartItem(item)
                }
            }
        }

    }


}

@Composable
fun SneakerCartItem(product: Product) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        modifier = Modifier.height(height = 105.dp).padding(vertical = 7.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            Modifier
                .fillMaxSize()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xffF7F7F9)
                ),
                modifier = Modifier.size(width = 87.dp, height = 85.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(
                    Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    //Заменить на асинх
                    AsyncImage(
                        model = product.image,
                        null,
                        modifier = Modifier.size(width = 86.dp, height = 55.dp)
                    )
                }


            }
            Spacer(Modifier.width(12.dp))
            Column(

            ) {
                Text(product.name.toString(), fontSize = 16.sp)
                Spacer(Modifier.height(6.dp))
                Row {
                    Text("₽ ${product.price}")
                    Spacer(Modifier.width(31.dp))
                    Text("1 ШТ")
                }

            }
        }
    }
}

