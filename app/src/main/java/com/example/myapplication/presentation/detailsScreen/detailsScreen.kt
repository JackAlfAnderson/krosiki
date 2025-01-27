package com.example.myapplication.presentation.detailsScreen

import android.util.Log
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.example.myapplication.R
import com.example.myapplication.domain.models.Product
import com.example.myapplication.presentation.detailsScreen.vm.DetailsScreenViewModel

@Composable
fun DetailsScreen(navController: NavController, detailsScreenViewModel: DetailsScreenViewModel) {

    detailsScreenViewModel.getList()

    val sneakers by detailsScreenViewModel.listOfProducts.collectAsState()
    Log.d("askdlfjlaskjdfl", sneakers.toString())
    val pagerState = rememberPagerState { sneakers.size }
    var currentPage = ""
    var currentKrosovok by remember { mutableStateOf("") }

    Column(
        Modifier
            .background(Color(0xffF7F7F9))
            .padding(20.dp)
            .fillMaxSize(),
    ) {
        Column(
            Modifier
                .background(Color(0xffF7F7F9))
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
                            navController.navigate("home")
                        }
                    )
                }
                Text(
                    "Популярное",
                    fontSize = 16.sp,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
                Box(
                    contentAlignment = Alignment.CenterEnd,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        painter = painterResource(R.drawable.bagnotifyicon),
                        null,
                        tint = Color.Unspecified,
                        modifier = Modifier.size(44.dp)
                    )


                }

            }
        }
        Spacer(Modifier.height(26.dp))

        Column {
            HorizontalPager(
                state = pagerState
            ) { current ->
                val alpha by animateFloatAsState(
                    if (pagerState.currentPage == current) 1f else 0f,
                    animationSpec = tween(300)
                )
                if (pagerState.layoutInfo.visiblePagesInfo.any { it.index == current}) {
                    Box(
                        modifier = Modifier.graphicsLayer(alpha = alpha)
                    ) {
                        OnSneakersItem(
                            sneakers[current]
                        )
                    }

                    currentPage = sneakers[current].name.toString()
                    currentKrosovok = currentPage
                }
                Log.d("asdfasdfasdf", currentPage)
            }
        }
    }
    Box(Modifier.padding(top = 400.dp, start = 20.dp)) {
        LazyRow {
            items(items = sneakers) {
                LazySneakersList(
                    it,
                    currentKrosovok
                )
                Spacer(Modifier.width(14.dp))
            }
        }
    }
}

@Composable
fun OnSneakersItem(product: Product) {
    Column {
        Column(
            Modifier.background(Color(0xffF7F7F9)),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(product.name.toString(), fontSize = 26.sp, modifier = Modifier.fillMaxWidth())
            Spacer(Modifier.height(13.dp))
            Text(
                product.category.toString(),
                fontSize = 16.sp,
                color = Color(0xff6a6a6a),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(14.dp))
            Text("₽${product.price.toString()}", modifier = Modifier.fillMaxWidth())
            Spacer(Modifier.height(25.dp))
            AsyncImage(
                model = product.image, "",
                modifier = Modifier.size(height = 125.dp, width = 241.dp)
            )

        }
        Column {
            Text(product.description.toString(), Modifier.padding(top = 156.dp))
        }
    }

}

@Composable
fun LazySneakersList(product: Product, name: String) {

    Card(
        modifier = Modifier.size(height = 56.dp, width = 56.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (name == product.name) Color(0xff48B2E7) else Color.White
        )
    ) {
        Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            Card(
                Modifier
                    .fillMaxSize()
                    .padding(3.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
            ) {
                Column(
                    Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center
                ) {
                    AsyncImage(
                        model = product.image, null, modifier = Modifier.size(width = 52.dp, height = 27.dp)
                    )
                }

            }
        }


    }
}