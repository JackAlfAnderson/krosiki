package com.example.myapplication.presentation.splash

import android.os.CountDownTimer
import android.window.SplashScreen
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.R
import com.example.myapplication.ui.theme.accent

@Composable
fun SplashScreen(navController: NavController) {
    Column(
        Modifier
            .fillMaxSize()
            .background(accent),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(id = R.drawable.icon_round),
            contentDescription = "",
            tint = Color.Unspecified,
            modifier = Modifier.size(129.dp)

        )
    }
    var timer: CountDownTimer = object : CountDownTimer(3000, 1000) {
        override fun onTick(millisUntilFinished: Long) {
            val m = millisUntilFinished
        }

        override fun onFinish() {
            navController.navigate("pager")
        }
    }.start()

}

@Preview(showBackground = true)
@Composable
private fun SplashPreview() {
    SplashScreen(
        navController = rememberNavController()
    )
}