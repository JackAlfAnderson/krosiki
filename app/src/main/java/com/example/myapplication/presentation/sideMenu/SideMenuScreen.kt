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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import com.example.myapplication.data.EmailManager
import com.example.myapplication.data.supabase.BaseAuthManager
import com.example.myapplication.presentation.sideMenu.vm.SideMenuViewModel

@Composable
fun SideMenuScreen() {

    Box(Modifier.fillMaxSize()) {
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
                modifier = Modifier.size(96.dp)
            )
            Spacer(Modifier.height(15.dp))
            Text("Эмануэль Кверти", fontSize = 20.sp, color = Color.White)
            Spacer(Modifier.height(60.dp))

            SideMenuItemScreen(
                SideMenuItem(
                    text = "Профиль",
                    icon = R.drawable.profileicon
                )
            )
            SideMenuItemScreen(
                SideMenuItem(
                    text = "Корзина",
                    icon = R.drawable.bagicon
                )
            )
            SideMenuItemScreen(
                SideMenuItem(
                    text = "Избранное",
                    icon = R.drawable.heart
                )
            )
            SideMenuItemScreen(
                SideMenuItem(
                    text = "Заказы",
                    icon = R.drawable.kamazicon
                )
            )
            SideMenuItemScreen(
                SideMenuItem(
                    text = "Уведомления",
                    icon = R.drawable.notificationicon
                )
            )
            SideMenuItemScreen(
                SideMenuItem(
                    text = "Настройки",
                    icon = R.drawable.gearicon
                )
            )

            Box(
                Modifier
                    .border(BorderStroke(2.dp, color = Color(0xFFFFFFFF)))
                    .fillMaxWidth()
                    .height(0.2.dp)
            )

            Spacer(Modifier.height(30.dp))
            SideMenuItemScreen(
                SideMenuItem(
                    text = "Выйти",
                    icon = R.drawable.exiticon
                )
            )


        }
    }
    Box(
        contentAlignment = Alignment.CenterEnd,
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(R.drawable.sidemenu),
            null,
            modifier = Modifier.size(height = 602.dp, width = 140.dp)
        )
    }


}

@Composable
fun SideMenuItemScreen(sideMenuItem: SideMenuItem) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(sideMenuItem.icon),
            null,
            tint = Color.White,
            modifier = Modifier.size(24.dp)
        )
        Spacer(Modifier.width(22.dp))
        Text(sideMenuItem.text, color = Color.White, fontSize = 16.sp)
    }
    Spacer(Modifier.height(30.dp))
}

@Preview
@Composable
private fun sadoa() {
    SideMenuScreen()
}

data class SideMenuItem(
    val text: String,
    val icon: Int
)