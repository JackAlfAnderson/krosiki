package com.example.myapplication.presentation.splash

import android.os.CountDownTimer
import android.window.SplashScreen
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.R

@Composable
fun SplashScreen(navController: NavController) {
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(id = R.drawable.icon_round),
            contentDescription = "",
            tint = Color.Unspecified

        )
    }
    var timer: CountDownTimer = object : CountDownTimer(3000, 1000) {
        override fun onTick(millisUntilFinished: Long) {
            val m = millisUntilFinished
        }

        override fun onFinish() {
            navController.navigate("signIn")
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