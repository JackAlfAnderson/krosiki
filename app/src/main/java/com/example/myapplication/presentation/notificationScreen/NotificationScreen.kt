package com.example.myapplication.presentation.notificationScreen

import android.icu.text.ListFormatter.Width
import android.util.Log
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapplication.R
import com.example.myapplication.data.EmailManager
import com.example.myapplication.data.app.App
import com.example.myapplication.domain.models.Notification
import com.example.myapplication.presentation.notificationScreen.vm.NotificationViewModel

@Composable
fun NotificationScreen(notificationViewModel: NotificationViewModel, navController: NavController) {

    val email = EmailManager(LocalContext.current).get()

    val notificationItems by notificationViewModel.listOfNotification.collectAsState()

    if (App.userId != "") {
        notificationViewModel.getList(App.userId)
    }
    val isShow by notificationViewModel.isShow.collectAsState()


    Column(
        Modifier
            .padding(20.dp)
            .fillMaxSize()
    ) {
        if (isShow) {
            CircularProgressIndicator(
                modifier = Modifier.size(1.dp)
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
            , contentAlignment = Alignment.Center
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
                Text(
                    "Уведомления",
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(start = 10.dp, top = 4.dp)
                        .clickable {

                        }
                )
            }

        }
        Spacer(Modifier.height(20.dp))
        LazyColumn {
            items(notificationItems){ item ->
                NotificationItemScreen(item)
            }
        }
    }
}

@Composable
fun NotificationItemScreen(notificationItem: Notification) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF7F7F9)
        )
    ) {
        Column(
            Modifier.padding(16.dp).fillMaxWidth()
        ) {
            Text(notificationItem.title ?: "", fontSize = 16.sp)
            Spacer(Modifier.height(8.dp))
            Text(notificationItem.description ?: "", fontSize = 12.sp)
            Spacer(Modifier.height(16.dp))
            Row {
                Text("${notificationItem.notification_date ?: "" }, ${notificationItem.notification_time ?: "" } ", fontSize = 12.sp, color = Color(0xFF707B81))

            }
        }

    }
    Spacer(Modifier.height(12.dp))
}