package com.example.myapplication.presentation.sideMenu

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R

@Composable
fun SideMenuScreen(modifier: Modifier = Modifier) {

    Box(

    ) {
        Image(
            painter = painterResource(R.drawable.sidemenu),
            null
        )
    }
    Column(
        Modifier
            .background(Color(0xFF48B2E7))
            .padding(35.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(
                R.drawable.emanuel
            ),
            null,
            modifier.size(96.dp)
        )
        Spacer(Modifier.height(15.dp))
        Text("Эмануэль Кверти", fontSize = 20.sp, color = Color.White)
        Spacer(Modifier.height(60.dp))

        ProfileItemScreen(
            ProfileItem(
                text = "Профиль",
                icon = R.drawable.profileicon
            )
        )
        ProfileItemScreen(
            ProfileItem(
                text = "Корзина",
                icon = R.drawable.bagicon
            )
        )
        ProfileItemScreen(
            ProfileItem(
                text = "Избранное",
                icon = R.drawable.heart
            )
        )
        ProfileItemScreen(
            ProfileItem(
                text = "Заказы",
                icon = R.drawable.kamazicon
            )
        )
        ProfileItemScreen(
            ProfileItem(
                text = "Уведомления",
                icon = R.drawable.notificationicon
            )
        )
        ProfileItemScreen(
            ProfileItem(
                text = "Настройки",
                icon = R.drawable.gearicon
            )
        )

        Box(
            Modifier.border(BorderStroke(2.dp, color = Color(0xFFFFFFFF))).fillMaxWidth().height(0.2.dp)
        )

        Spacer(Modifier.height(30.dp))
        ProfileItemScreen(
            ProfileItem(
                text = "Выйти",
                icon = R.drawable.exiticon
            )
        )


    }

}

@Composable
fun ProfileItemScreen(profileItem: ProfileItem) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(profileItem.icon),
            null,
            tint = Color.White,
            modifier = Modifier.size(24.dp)
        )
        Spacer(Modifier.width(22.dp))
        Text(profileItem.text, color = Color.White, fontSize = 16.sp)
    }
    Spacer(Modifier.height(30.dp))
}

@Preview(showBackground = true)
@Composable
private fun SideMenuPreview() {
    SideMenuScreen()
}

data class ProfileItem(
    val text: String,
    val icon: Int
)