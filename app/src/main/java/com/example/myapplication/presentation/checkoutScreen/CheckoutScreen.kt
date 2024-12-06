package com.example.myapplication.presentation.checkoutScreen

import android.widget.Space
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.R
import com.example.myapplication.ui.theme.ButtonSuperColor

@Composable
fun CheckoutScreen(navController: NavController) {

    var isShow by remember {
        mutableStateOf(false)
    }

    CustomCheckoutDialog(
        isShow,
            {
            navController.navigate("home")
        },
            {
            navController.navigate("home")
        }
    )

    Column(
        Modifier.padding(horizontal = 20.dp)
    ) {
        Spacer(Modifier.height(23.dp))
        Column(Modifier.fillMaxWidth()) {



            Box(
                Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center,
            ){
                Box(Modifier.fillMaxWidth()) {
                    Icon(
                        painter = painterResource(R.drawable.back_icon),
                        null,
                        tint = Color.Unspecified,
                        modifier = Modifier.clickable {
                            navController.navigate("pager")
                        }
                    )
                }

                Text("Корзина")
            }

        }
        Spacer(Modifier.height(11.dp))
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
                    modifier = Modifier.fillMaxWidth())
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
                            Text("Email")
                        }
                        Box(
                            Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd
                        ){
                            Column(
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.size(40.dp)
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.pencilicon),
                                    null,
                                    tint = Color.Unspecified
                                )
                            }
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
                            Text("Телефон")
                        }
                        Box(
                            Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd
                        ){
                            Column(
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.size(40.dp)
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.pencilicon),
                                    null,
                                    tint = Color.Unspecified
                                )
                            }
                        }

                    }
                }

                Spacer(Modifier.height(12.dp))
                Text(
                    "Адрес",
                    modifier = Modifier.fillMaxWidth())
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("1082 Аэропорт, Нигерии")
                    Box(
                        Modifier
                            .fillMaxWidth()
                            .padding(end = 10.dp), contentAlignment = Alignment.CenterEnd
                    ){
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .size(40.dp)
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.pencilicon),
                                null,
                                tint = Color.Unspecified
                            )
                        }
                    }
                }
                Image(
                    painter = painterResource(R.drawable.mapimage),
                    null,
                    modifier = Modifier
                        .height(101.dp)
                        .width(295.dp)
                        .clickable {
                            navController.navigate("map")
                        }
                )
                Spacer(Modifier.height(12.dp))
                Text("Способ оплаты", modifier = Modifier.fillMaxWidth())
                Spacer(Modifier.height(12.dp    ))
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
                                Text("**** **** ")
                                Text("0696 4629")
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
        Spacer(Modifier.height(25.dp))

        Row() {
            Text(
                "Сумма",
                color = Color(0xFF6A6A6A)
            )
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
                Text(
                    "₽753.95"
                )
            }

        }
        Spacer(Modifier.height(7.dp))
        Row() {
            Text(
                "Доставка",
                color = Color(0xFF6A6A6A)
            )
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
                Text(
                    "₽60.20"
                )
            }
        }
        Spacer(Modifier.height(23.dp))
        Image(
            painter = painterResource(R.drawable.pounctear), null, modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(20.dp))
        Row() {
            Text(
                "Итого"
            )
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
                Text(
                    "₽814.15",
                    color = Color(0xFF48B2E7)
                )
            }
        }
        Spacer(Modifier.height(30.dp))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            onClick = {
                isShow = !isShow
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = ButtonSuperColor
            ),
            shape = RoundedCornerShape(14.dp)
        ) {
            Text(
                "Подтвердить"
            )
        }

    }

}

@Composable
fun CustomCheckoutDialog(
    isShow: Boolean,
    onDismissRequest: () -> Unit,
    onNextPage: () -> Unit
) {
    if (isShow) {
        Dialog(
            onDismissRequest = {
                onDismissRequest()
            }
        ) {
            Card(
                modifier = Modifier.clickable {
                    onNextPage()
                },
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(
                        start = 49.dp,
                        top = 30.dp,
                        end = 49.dp,
                        bottom = 30.dp
                    )
                ) {
                    Card(
                        shape = RoundedCornerShape(200.dp),
                        modifier = Modifier.size(134.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFFDFEFFF)
                        )

                    ) {
                        Column(Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally) {
                            Image(
                                painter = painterResource(R.drawable.conrgateicon),
                                null,
                                Modifier.size(86.dp)
                            )
                        }

                    }
                    Spacer(Modifier.height(24.dp))
                    Text(
                        "Вы успешно оформили заказ",
                        modifier = Modifier.width(120.dp).height(84.dp),
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp
                    )
                    Spacer(Modifier.height(30.dp))
                    Button(
                        modifier = Modifier
                            .height(50.dp),
                        onClick = {
                            onNextPage()
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = ButtonSuperColor
                        ),
                        shape = RoundedCornerShape(14.dp)
                    ) {
                        Text(
                            "Вернуться к покупкам"
                        )
                    }

                }
            }

        }
    }
}

@Preview
@Composable
private fun DialogPreview() {
    CustomCheckoutDialog(true, {}, {})
}


@Preview(showBackground = true)
@Composable
private fun CheckoutPreview() {
    CheckoutScreen(rememberNavController())
}
