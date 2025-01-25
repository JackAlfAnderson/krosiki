package com.example.myapplication.presentation.home

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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.example.myapplication.R
import com.example.myapplication.data.app.App
import com.example.myapplication.domain.models.Product
import com.example.myapplication.presentation.home.vm.HomeViewModel
import com.example.myapplication.ui.theme.newPeninium

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    homeViewModel: HomeViewModel,
    categories: List<CategoryItem>
) {

    var search by remember { mutableStateOf("") }

    homeViewModel.getList()

    val sneakers by homeViewModel.listOfProducts.collectAsState()
    val isShow by homeViewModel.isShow.collectAsState()
    var listOfSearch by remember { mutableStateOf(listOf<String>()) }

    App.listProducts = sneakers.toMutableList()

    Box {
        if (isShow) {
            CircularProgressIndicator(
                modifier = Modifier.size(1.dp)
            )
        }
        Box {
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
                                    navController.navigate("sideMenu")
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
                        Column(
                            modifier = Modifier.background(Color(0xFFF7F7F9))
                        ) {
                            SearchBar(
                                colors = SearchBarDefaults.colors(
                                    containerColor = Color.White,
                                    dividerColor = Color.Transparent
                                ),
                                query = search,
                                onQueryChange = {
                                    search = it
                                },
                                onSearch = {

                                },
                                active = true,
                                onActiveChange = {

                                },
                                placeholder = {
                                    Row {
                                        Icon(
                                            painter = painterResource(R.drawable.icon),
                                            null,
                                            tint = Color.Unspecified
                                        )
                                        Spacer(modifier = Modifier.width(12.dp))
                                        Text("Поиск", fontSize = 12.sp)

                                    }
                                    Box(contentAlignment = Alignment.CenterEnd, modifier = Modifier.fillMaxWidth()) {
                                        Row() {
                                            Icon(
                                                painter = painterResource(R.drawable.rectangle_846),
                                                null,
                                                modifier = Modifier
                                                    .height(24.dp)
                                                    .width(2.dp)
                                            )
                                            Spacer(Modifier.width(12.dp))
                                            Icon(
                                                painter = painterResource(R.drawable.vector),
                                                null
                                            )
                                        }
                                    }
                                },
                                shape = RoundedCornerShape(14.dp)
                            ) {
                                LazyColumn(
                                    Modifier.padding(20.dp)
                                ) {
                                    items(
                                        listOfSearch
                                    ){ item ->
                                        RecentSearchItem(item)
                                        Spacer(Modifier.height(16.dp))
                                    }
                                }
                            }
                        }

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
                            CategoryRow(
                                item,
                                onCategoryClick = {
                                    navController.navigate("Category/$it")
                                }
                            )
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
                                    navController.navigate("popular")
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
        ) {

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun Something() {
    var listOfSearch = listOf("Some", "Thing","Some", "Thing")
    var search = ""
    Column(
        modifier = Modifier.background(Color(0xFFF7F7F9))
    ) {
        SearchBar(
            colors = SearchBarDefaults.colors(
                containerColor = Color.White,
                dividerColor = Color.Transparent
            ),
            query = search,
            onQueryChange = {
                search = it
            },
            onSearch = {

            },
            active = true,
            onActiveChange = {

            },
            placeholder = {
                Row {
                    Icon(
                        painter = painterResource(R.drawable.icon),
                        null,
                        tint = Color.Unspecified
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text("Поиск", fontSize = 12.sp)

                }
                Box(contentAlignment = Alignment.CenterEnd, modifier = Modifier.fillMaxWidth()) {
                    Row() {
                        Icon(
                            painter = painterResource(R.drawable.rectangle_846),
                            null,
                            modifier = Modifier
                                .height(24.dp)
                                .width(2.dp)
                        )
                        Spacer(Modifier.width(12.dp))
                        Icon(
                            painter = painterResource(R.drawable.vector),
                            null
                        )
                    }
                }
            },
            shape = RoundedCornerShape(14.dp)
        ) {
            LazyColumn(
                Modifier.padding(20.dp)
            ) {
                items(
                    listOfSearch
                ){ item ->
                    RecentSearchItem(item)
                    Spacer(Modifier.height(16.dp))
                }
            }
        }
    }

}

@Composable
fun RecentSearchItem(text: String) {
    Row{
        Icon(painter = painterResource(R.drawable.clockicon), null)
        Spacer(Modifier.width(12.dp))
        Text(text, fontSize = 14.sp)
    }


}

@Composable
fun SneakersScreen(
    product: Product
) {

    var isLiked by remember { mutableStateOf(false) }
    Column(
        Modifier.padding(8.dp)
    ) {
        Card(
            modifier = Modifier
                .width(160.dp)
                .height(182.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
        ) {
            Column(

            ) {
                Box {
                    Card(
                        shape = RoundedCornerShape(200.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFFF7F7F9)
                        ),
                        modifier = Modifier
                            .padding(start = 9.dp, top = 9.dp)
                    ) {
                        Icon(
                            painter = if (isLiked) painterResource(R.drawable.fillheart) else painterResource(
                                R.drawable.heart
                            ),
                            null,
                            modifier = Modifier
                                .padding(8.dp)
                                .size(16.dp)
                                .clickable {
                                    isLiked = !isLiked
                                },
                            tint = Color.Unspecified
                        )
                    }
                    Column(
                        Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(Modifier.height(20.dp))
                        AsyncImage(
                            modifier = Modifier
                                .width(118.dp)
                                .height(70.dp),
                            model = product.image,
                            contentDescription = null
                        )
                    }
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 9.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        if (product.best_seller == true) "BEST SELLER" else "",
                        color = Color(0xFF48B2E7),
                        fontSize = 12.sp
                    )
                    Spacer(Modifier.height(8.dp))
                    Text(
                        if (product.name.toString().length > 16) product.name.toString()
                            .take(13) + "..." else product.name.toString(),
                        color = Color(0xFF6A6A6A),
                        fontSize = 16.sp
                    )
                    Row(
                        Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            "₽" + product.price.toString(),
                            fontSize = 14.sp,
                            fontFamily = newPeninium
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.End
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.plusicon),
                                null,
                                tint = Color.Unspecified
                            )
                        }
                    }
                }
            }
        }
    }

}

@Preview
@Composable
private fun Some() {
    SneakersScreen(
        product = Product(
            null,
            null,
            null,
            null,
            752.00,
            null,
            null,
            null,
        )
    )
}

@Composable
fun CategoryRow(categoryItem: CategoryItem, onCategoryClick: (String) -> Unit) {
    Card(
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = categoryItem.containerColor
        ),
        modifier = Modifier
            .padding(end = 16.dp)
            .clickable {
                onCategoryClick(categoryItem.text)
            }
    ) {
        Text(
            categoryItem.text,
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

data class CategoryItem(
    val text: String,
    var containerColor: Color
)