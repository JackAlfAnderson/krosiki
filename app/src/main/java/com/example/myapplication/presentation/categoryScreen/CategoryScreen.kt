package com.example.myapplication.presentation.categoryScreen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import com.example.myapplication.data.app.App
import com.example.myapplication.presentation.home.CategoryItem

@Composable
fun CategoryScreen(categories: List<CategoryItem>, category: String) {

    var isChoosen by remember { mutableStateOf(true) }

    CategoryButtons(isChoosen)

    Column(
        Modifier.background(Color(0xFFF7F7F9))
    ) {
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
                        tint = Color.Unspecified
                    )
                }
                Text(
                    "Популярное",
                    fontSize = 16.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            isChoosen = !isChoosen
                        },
                    textAlign = TextAlign.Center
                )
                Spacer(Modifier.height(22.dp))
                Text(
                    text = "Категории", fontSize = 16.sp
                )
                Spacer(Modifier.height(19.dp))
                
                Spacer(Modifier.height(25.dp))
            }
            Spacer(Modifier.height(20.dp))

        }
    }
}




@Composable
fun CategoryButtons(isChoosen: Boolean) {
    Button(
        onClick = {

        },
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isChoosen) Color.Cyan else Color.Black
        )
    ) {
        Text("GNILOY DESIGNER MATULE")
    }
}


//@Preview
//@Composable
//private fun Prev() {
//    CategoryScreen(whichScreen = 10, categories = listOf(
//        CategoryItem("Все", onClick = {
//
//        },
//            containerColor = Color.White
//        ),
//        CategoryItem("Outdoor", onClick = {
//
//        },
//            containerColor = Color.White
//        ),
//        CategoryItem("Tennis", onClick = {
//
//        },
//            containerColor = Color.White
//        ),
//
//        CategoryItem("Running", onClick = {
//
//        },
//            containerColor = Color.White
//        )
//        )
//    )
//}