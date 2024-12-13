package com.example.myapplication.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import io.ktor.util.hex

@Composable
fun HomeScreen() {

    var poisk by remember { mutableStateOf("") }



    //Categories
    val categories = listOf(
        CategoryItem("Все"),
        CategoryItem("Outdoor"),
        CategoryItem("Tennis"),
        CategoryItem("Running"),
    )

    //Sneakers
    val sneakers = listOf(
        SneakersItem(
            R.drawable.vtoroykross,
            "BEST SELLER",
            "Nike Air Max",
            752.00f
        ),
        SneakersItem(
            R.drawable.vtoroykross,
            "BEST SELLER",
            "Nike Air Max",
            752.00f
        ),
        SneakersItem(
            R.drawable.vtoroykross,
            "BEST SELLER",
            "Nike Air Max",
            752.00f
        ),
        SneakersItem(
            R.drawable.vtoroykross,
            "BEST SELLER",
            "Nike Air Max",
            752.00f
        )
    )
    Box {
        Box{
            Column(
                Modifier.background(Color(0xFFF7F7F9))
            ) {
                Column(
                    Modifier
                        .fillMaxSize()
                        .padding(20.dp)
                        .background(Color(0xFFF7F7F9))
                ) {
                    Box(
                        modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center
                    ) {
                        Box(
                            Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterStart
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.menuicon),
                                null,
                                tint = Color.Unspecified,
                                modifier = Modifier.clickable {
                                    
                                }
                            )
                        }
                        Box(

                        ) {
                            Icon(
                                painter = painterResource(R.drawable.highlighticon),
                                contentDescription = null,
                                tint = Color.Unspecified
                            )
                            Text(
                                "Главная",
                                fontSize = 32.sp,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(start = 10.dp, top = 4.dp)
                            )
                        }

                        Box(
                            Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.usersbagicon),
                                null,
                                tint = Color.Unspecified,
                                modifier = Modifier.clickable {

                                }
                            )
                        }
                    }
                    Spacer(Modifier.height(26.dp))
                    Box() {
                        TextField(
                            value = poisk,
                            onValueChange = {
                                poisk = it
                            },
                            singleLine = true,
                            label = {
                                Text("Поиск")
                            },
                            modifier = Modifier
                                .width(269.dp)
                                .height(52.dp),
                            shape = RoundedCornerShape(14.dp),
                            colors = TextFieldDefaults.colors(
                                unfocusedContainerColor = Color.White,
                                unfocusedIndicatorColor = Color.Transparent,
                                focusedContainerColor = Color.White,
                                focusedIndicatorColor = Color.Transparent,
                            ),
                            trailingIcon = {
                                Icon(
                                    painter = painterResource(R.drawable.icon),
                                    null,
                                    tint = Color.Unspecified
                                )
                            }
                        )
                        Box(
                            contentAlignment = Alignment.CenterEnd,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Icon(
                                painterResource(R.drawable.settingsicon),
                                null,
                                tint = Color.Unspecified,
                                modifier = Modifier.clickable {

                                }
                            )
                        }


                    }
                    Spacer(Modifier.height(22.dp))
                    Text(
                        text = "Категории", fontSize = 16.sp
                    )
                    Spacer(Modifier.height(19.dp))
                    LazyRow() {
                        items(categories) { item ->
                            CategoryScreen(item.text)
                        }
                    }
                    Spacer(Modifier.height(25.dp))
                    Box {
                        Box(
                            Modifier
                                .fillMaxWidth()
                                .padding(end = 10.dp),
                            contentAlignment = Alignment.CenterEnd,
                        ) {
                            Text(
                                "Все",
                                color = Color(0xFF48B2E7),
                                modifier = Modifier.clickable {

                                }
                            )
                        }
                        Text("Популярное", fontSize = 16.sp)
                    }
                    Spacer(Modifier.height(30.dp))

                    LazyRow() {
                        items(sneakers) { sneaker ->
                            SneakersScreen(sneaker)
                        }
                    }

                    Spacer(Modifier.height(29.dp))
                    Box {
                        Box(
                            Modifier
                                .fillMaxWidth()
                                .padding(end = 10.dp),
                            contentAlignment = Alignment.CenterEnd,
                        ) {
                            Text(
                                "Все",
                                color = Color(0xFF48B2E7),
                                modifier = Modifier.clickable {

                                }
                            )
                        }
                        Text("Акции", fontSize = 16.sp)
                    }
                    Spacer(Modifier.height(20.dp))
                    Column(
                        Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(R.drawable.akciaimage),
                            contentDescription = null,
                            modifier = Modifier
                                .height(95.dp)
                                .fillMaxWidth()
                                .clickable {

                                }
                        )
                    }
                }
            }
        }
        Box(
            contentAlignment = Alignment.BottomCenter,
            modifier =
                Modifier.fillMaxSize()
        ){

        }
    }
}

    @Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    HomeScreen()
}

@Composable
fun SneakersScreen(sneakersItem: SneakersItem) {

    var isLiked by remember { mutableStateOf(false) }

    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        modifier = Modifier.padding(end = 15.dp)
    ) {
        Box(
            modifier = Modifier.padding(9.dp)
        ) {
            Card(
                shape = RoundedCornerShape(200.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFF7F7F9)
                ),
                modifier = Modifier.size(28.dp),
            ) {
                Icon(
                    painter = if (isLiked) painterResource(R.drawable.fillheart) else painterResource(R.drawable.heart),
                    null,
                    modifier = Modifier
                        .padding(8.dp)
                        .clickable {
                            isLiked = !isLiked
                        },
                    tint = Color.Unspecified
                )
            }


            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(painterResource(sneakersItem.image), null,
                    Modifier
                        .height(70.dp)
                        .width(150.dp))
            }
        }
        Column(
            modifier = Modifier.padding(9.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(sneakersItem.title, color = Color(0xFF48B2E7), fontSize = 12.sp)
            Spacer(Modifier.height(8.dp))
            Text(sneakersItem.subtitle, color = Color(0xFF6A6A6A), fontSize = 16.sp)
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("₽" + sneakersItem.coast.toString() , fontSize = 14.sp)
                Spacer(Modifier.width(77.dp))
                Icon(painter = painterResource(R.drawable.plusicon), null, tint = Color.Unspecified)
            }
        }
    }

}

@Composable
fun CategoryScreen(text: String) {
    Card(
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        modifier = Modifier
            .padding(end = 16.dp)
            .clickable {

            }
    ) {
        Text(
            text,
            fontSize = 12.sp,
            modifier = Modifier.padding(
                start = 43.dp,
                top = 16.dp,
                end = 43.dp,
                bottom = 16.dp
            )
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun Sneakers() {
    SneakersScreen(sneakersItem = SneakersItem(R.drawable.vtoroykross, "BEST SELLER", "Nike Air Max", 752.00f))
}

data class CategoryItem(
    val text: String
)

data class SneakersItem(
    val image:Int,
    val title: String,
    val subtitle:String,
    val coast: Float
)