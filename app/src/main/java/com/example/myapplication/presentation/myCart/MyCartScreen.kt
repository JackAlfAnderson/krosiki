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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R

@Composable
fun MyCart() {
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
            Spacer(Modifier.height(16.dp))
            SneakerCartItem()
            Spacer(Modifier.height(14.dp))
            SneakerCartItem()
            Spacer(Modifier.height(14.dp))
            SneakerCartItem()
        }

    }


}

@Composable
fun SneakerCartItem() {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        modifier = Modifier.height(height = 105.dp),
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
                    Image(
                        painter = painterResource(R.drawable.tretikross),
                        null,
                        modifier = Modifier.size(width = 86.dp, height = 55.dp)
                    )
                }


            }
            Spacer(Modifier.width(12.dp))
            Column(

            ) {
                Text("Nike Air Max 270 Essential", fontSize = 16.sp)
                Spacer(Modifier.height(6.dp))
                Row {
                    Text("₽814.15")
                    Spacer(Modifier.width(31.dp))
                    Text("1 ШТ")
                }

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MyCartPreview() {
    MyCart()
}