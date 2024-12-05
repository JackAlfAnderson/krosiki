package com.example.myapplication.presentation.pagerScreen

import androidx.compose.animation.core.animate
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.R
import com.example.myapplication.ui.theme.ButtonSuperColor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@Composable
fun PagerScreen(navController: NavController) {

    val pages = listOf(
        OnBoardingItem(R.drawable.perviykross, "", ""),
        OnBoardingItem(R.drawable.vtoroykross, "Начнем путешествие", "Умная, великолепная и модная коллекция Изучите сейчас"),
        OnBoardingItem(R.drawable.tretikross, "У вас есть сила, чтобы", "В вашей комнате много красивых и привлекательных растений")
    )

    var pagerState = rememberPagerState { pages.size }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(listOf(Color(0xFF48B2E7), Color(0xFF44A9DC), Color(0xFF2B6B8B))))
    ) {
        HorizontalPager(pagerState) { page ->
            val alpha by animateFloatAsState(
                if (pagerState.currentPage == page) 1f else 0f,
                animationSpec = tween(300)
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer(alpha = alpha)
            ){
                if (page == 0) {
                    OnBoardingFirstScreen(pages[page])
                } else {
                    OnBoardingScreen(pages[page])
                }
            }

        }

    }
    val paddingIndicator = animateDpAsState(
        targetValue = if(pagerState.currentPage == 0) 222.dp else 181.dp,
        animationSpec = tween(durationMillis = 300)
    )
    Row (modifier = Modifier
        .wrapContentHeight()
        .fillMaxSize()
        .padding(bottom = paddingIndicator.value),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.Center
    ){
        repeat(pagerState.pageCount){
            val colorBox = if(pagerState.currentPage == it) Color.White else Color(0xFF2B6B8B)
            val width by animateDpAsState(
                if (pagerState.currentPage == it) 43.dp else 28.dp,
                tween(durationMillis = 300)
            )
            Box(
                modifier = Modifier
                    .padding(2.dp)
                    .clip(CircleShape)
                    .background(colorBox)
                    .height(5.dp)
                    .width(width)
            )
        }

    }
    Column(
        Modifier
            .fillMaxSize()
            .padding(bottom = 36.dp, start = 20.dp, end = 20.dp)
            .height(50.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val buttonText = if (pagerState.currentPage == 0) "Начать" else "Далее"
        val scope = rememberCoroutineScope()
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                scope.launch {
                    if (pagerState.currentPage != 2){
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    } else {
                        navController.navigate("signIn")
                    }

                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White
                
            ),
            shape = RoundedCornerShape(13.dp)
        ){
            Text(buttonText, color = Color.Black)
        }
    }

}

@Composable
fun OnBoardingScreen(
    item: OnBoardingItem
) {
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(item.image),
            contentDescription = "",
            modifier = Modifier.fillMaxWidth().height(375.dp)
        )
        Text(
            text = item.title,
            fontSize = 34.sp,
            color = Color.White,
            textAlign = TextAlign.Center
        )
        Text(
            text = item.subtitle,
            fontSize = 16.sp,
            color = Color.White,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun OnBoardingFirstScreen(
    item: OnBoardingItem
) {
    Column(
        modifier = Modifier
            .padding(top = 29.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            fontSize = 30.sp,
            text = "Добро пожаловать".toUpperCase(),
            textAlign = TextAlign.Center,
            color = Color.White,
            modifier = Modifier.width(267.dp)
        )
    }
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Spacer(Modifier.height(20.dp))
        Image(
            painter = painterResource(item.image),
            contentDescription = "",
            modifier = Modifier.fillMaxWidth().height(420.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun OnBoardPreview(){
    PagerScreen(rememberNavController())
}

data class OnBoardingItem(
    val image: Int,
    val title: String,
    val subtitle: String
)