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
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R

@Composable
fun HomeScreen() {

    var poisk by remember { mutableStateOf("") }

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
    

    Column(Modifier.fillMaxSize().padding(20.dp).background(Color(0xFFF7F7F9))) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ){
            Icon(
                painter = painterResource(R.drawable.menuicon),
                null,
                tint = Color.Unspecified
            )
            Text(
                "Главная",
                fontSize = 32.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 78.dp)

            )
            Icon(
                painter = painterResource(R.drawable.usersbagicon),
                null,
                tint = Color.Unspecified
            )
        }
        Spacer(Modifier.height(26.dp))
        Row {
            TextField(
                value = poisk,
                onValueChange = {
                    poisk = it
                },
                label = {
                    Text("Поиск")
                },
                modifier = Modifier.width(269.dp).height(52.dp),
                shape = RoundedCornerShape(14.dp),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.White,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                trailingIcon = {
                    Icon(
                        painter = painterResource(R.drawable.icon), null, tint = Color.Unspecified
                    )
                }
            )
            Spacer(Modifier.width(14.dp))
            Icon(painterResource(R.drawable.settingsicon), null, tint = Color.Unspecified)
        }
        Spacer(Modifier.height(22.dp))
        Text(
            text = "Категории", fontSize = 16.sp
        )
        LazyRow() {

        }


        Spacer(Modifier.height(200.dp))
        Column(

        ) {
            LazyRow(){
                items(sneakers){ sneaker ->
                    SneakersScreen(sneaker)
                }
            }
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
    Column(
        Modifier.clip(RoundedCornerShape(30.dp))
    ) {
        Box(
            modifier = Modifier.padding(9.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.heart),
                null,
                modifier = Modifier.clickable {

                }
            )
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

@Preview(showBackground = true)
@Composable
private fun Sneakers() {
    SneakersScreen(sneakersItem = SneakersItem(R.drawable.vtoroykross, "BEST SELLER", "Nike Air Max", 752.00f))
}

data class SneakersItem(
    val image:Int,
    val title: String,
    val subtitle:String,
    val coast: Float
)