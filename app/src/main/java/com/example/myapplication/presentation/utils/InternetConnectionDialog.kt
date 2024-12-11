package com.example.myapplication.presentation.utils

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.myapplication.data.connection.connectionCheck

@Composable
fun InternetConnectionDialog(context: Context) {

    var networkState by remember {
        mutableStateOf(true)
    }

    networkState = connectionCheck(context)

    if (!networkState){
        Dialog(
            onDismissRequest = {
                networkState = connectionCheck(context)
            }
        ) {
            Card(
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(
                    modifier = Modifier.padding(30.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        "Ошибка", Modifier.padding(horizontal = 50.dp)
                    )
                    Text("Нет подключения к интернету", Modifier.padding(horizontal = 20.dp))
                }
            }

        }
    }



}