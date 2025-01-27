package com.example.myapplication.presentation.orderDetails

import androidx.compose.foundation.Image
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
import coil3.compose.AsyncImage
import com.example.myapplication.R
import com.example.myapplication.domain.models.OrderItem
import com.example.myapplication.presentation.detailsScreen.DetailsScreen

@Composable
fun OrderDetailsScreen() {

    Column(
        Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
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
                "88005553535",
                fontSize = 16.sp,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )


        }
        Spacer(Modifier.height(17.dp))
        Text(
            "7 мин назад",
            color = Color(0xff6A6A6A),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.End
        )
        Spacer(Modifier.height(17.dp))
        DetailsSneakerItem()
        Spacer(Modifier.height(12.dp))
        DetailsSneakerItem()
        Spacer(Modifier.height(12.dp))
        Card(
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ) {


            Column(
                Modifier.padding(horizontal = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(Modifier.height(16.dp))
                Text(
                    "Контактная информация",
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(16.dp))
                Column(Modifier.fillMaxWidth()) {
                    Row(

                    ) {
                        Card(
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier.size(40.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color(0xFFF7F7F9)
                            )
                        ) {

                            Column(
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.size(40.dp)
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.someemailicon),
                                    null,
                                    tint = Color.Unspecified
                                )
                            }
                        }
                        Spacer(Modifier.width(12.dp))
                        Column {
                            Text("emmanuel@gmail.com")
                            Spacer(Modifier.height(4.dp))
                            Text("Email", color = Color(0xff6A6A6A))
                        }

                    }
                }
                Spacer(Modifier.height(16.dp))
                Column(Modifier.fillMaxWidth()) {
                    Row(

                    ) {
                        Card(
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier.size(40.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color(0xFFF7F7F9)
                            )
                        ) {

                            Column(
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.size(40.dp)
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.phoneicon),
                                    null,
                                    tint = Color.Unspecified
                                )
                            }
                        }
                        Spacer(Modifier.width(12.dp))
                        Column {
                            Text("+234-811-732-5298")
                            Spacer(Modifier.height(4.dp))
                            Text("Телефон", color = Color(0xff6A6A6A))
                        }


                    }
                }

                Spacer(Modifier.height(12.dp))
                Text(
                    "Адрес",
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(12.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("1082 Аэропорт, Нигерии", color = Color(0xff6A6A6A), modifier = Modifier.fillMaxWidth())

                }
                Spacer(Modifier.height(16.dp))
                Image(
                    painter = painterResource(R.drawable.mapimage),
                    null,
                    modifier = Modifier
                        .height(130.dp)
                        .width(315.dp)
                        .clickable {
//                            navController.navigate("map")
                        }
                )
                Text("Способ оплаты", modifier = Modifier.fillMaxWidth())
                Spacer(Modifier.height(12.dp))
                Column(Modifier.fillMaxWidth()) {
                    Row {
                        Card(
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier.size(40.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color(0xFFF7F7F9)
                            )
                        ) {
                            Column(
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.size(40.dp)
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.cardicon),
                                    null,
                                    tint = Color.Unspecified,
                                    modifier = Modifier.size(30.dp)
                                )
                            }
                        }
                        Spacer(Modifier.width(12.dp))
                        Column() {
                            Text("DbL Card")
                            Spacer(Modifier.height(4.dp))
                            Row {
                                Text("**** **** ", color = Color(0xff6A6A6A))
                                Text("0696 4629", color = Color(0xff6A6A6A))
                            }
                        }
                        Spacer(Modifier.width(110.dp))
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.size(40.dp)
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.downarrow),
                                null,
                                tint = Color.Unspecified
                            )
                        }
                    }
                    Spacer(Modifier.height(16.dp))

                }
            }

        }
    }


}

@Composable
fun DetailsSneakerItem(

) {
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
                Text("Nike Air Max 270 Essential")
                Spacer(Modifier.height(42.dp))
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
private fun OrderDetailsScreenPreview() {
    OrderDetailsScreen()
}