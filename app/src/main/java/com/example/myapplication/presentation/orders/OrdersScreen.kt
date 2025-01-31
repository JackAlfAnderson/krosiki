package com.example.myapplication.presentation.orders

import android.util.Log
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
import com.example.myapplication.data.app.App
import com.example.myapplication.domain.models.OrderItem
import com.example.myapplication.presentation.orders.VM.OrdersScreenViewModel

@Composable
fun OrdersScreen(ordersScreenVIewModel: OrdersScreenViewModel) {

    val email = EmailManager(LocalContext.current).get()

    val userId = App.userId

    Log.d("sddf", userId)
    LaunchedEffect(Unit) {
        ordersScreenVIewModel.getOrders(userId)
        ordersScreenVIewModel.getOrderItems(userId)
        ordersScreenVIewModel.getProductsFromOrder(userId)

    }

    val listOfOrders by ordersScreenVIewModel.listOfOrders.collectAsState()
    val listOfOrderItems by ordersScreenVIewModel.listOfOrderItems.collectAsState()

    Column(
        modifier = Modifier
            .background(Color(0xFFF7F7F9))
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Column {
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
                    "Заказы",
                    fontSize = 16.sp,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )


            }
        }
        Spacer(Modifier.height(16.dp))
        Column {
            Text("Недавний",
                fontSize = 18.sp,
                modifier = Modifier.fillMaxWidth())
        }
        LazyColumn {
            items(listOfOrderItems){
                OrderItem(it, ordersScreenVIewModel)
            }
        }

        Column {
            Text("Вчера",
                fontSize = 18.sp,
                modifier = Modifier.fillMaxWidth())
        }

    }
}

@Composable
fun OrderItem(orderItem: OrderItem, ordersScreenViewModel: OrdersScreenViewModel) {

    ordersScreenViewModel.getProductById(orderItem.product_id.toString())
    val product by ordersScreenViewModel.product.collectAsState()

    Column(
        Modifier.padding(vertical = 7.dp)
    ) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            modifier = Modifier.height(height = 105.dp),
            shape = RoundedCornerShape(8.dp),
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
                    Row {
                        Text("№ ${orderItem.order_id}", color = Color(0xff48B2E7))
                        Text(
                            "7 мин назад",
                            color = Color(0xff6A6A6A),
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.End
                        )
                    }

                    Spacer(Modifier.height(9.dp))
                    Text(product.name.toString())
                    Spacer(Modifier.height(9.dp))
                    Row {
                        Text("₽${product.price}")
                        Spacer(Modifier.width(31.dp))
                        Text("1 ШТ")
                    }

                }
            }
        }
    }

}

