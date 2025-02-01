package com.example.myapplication.presentation.profile

import android.util.Log
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.example.myapplication.R
import com.example.myapplication.data.EmailManager
import com.example.myapplication.data.app.App
import com.example.myapplication.presentation.profile.vm.ProfileViewModel

@Composable
fun ProfileScreen(profileViewModel: ProfileViewModel, navController: NavController) {

    val email = EmailManager(LocalContext.current).get()

    val isShow by profileViewModel.isShow.collectAsState()

    LaunchedEffect(Unit) {
        profileViewModel.getProfile(email)
        profileViewModel.getImageUrl(App.userId)
    }
    val profile by profileViewModel.profile.collectAsState()

    val userImage by profileViewModel.userImage.collectAsState()

    Log.d("chkeckasdlkf", profile.toString())
    //Remember values
    var userName by remember { mutableStateOf("") }
    userName = profile.name

    var userSurname by remember { mutableStateOf("") }
    userSurname = profile.surname ?: ""

    var userAddress by remember { mutableStateOf(profile.address) }
    userAddress = profile.address ?: ""

    var userPhone by remember { mutableStateOf(profile.phone) }
    userPhone = profile.phone ?: ""

    Column(
        Modifier
            .fillMaxSize()
            .padding(20.dp)) {
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
                    "Профиль",
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(start = 10.dp, top = 4.dp).clickable {

                    }
                )
            }

            Box(
                Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd
            ) {
                Icon(
                    painter = painterResource(R.drawable.pencilicon),
                    null,
                    tint = Color.Unspecified,
                    modifier = Modifier.clickable {
                        navController.navigate("editProfile")
                    }
                )
            }
        }
        Spacer(Modifier.height(48.dp))
        Column(
            Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = userImage,
                null,
                modifier = Modifier.size(96.dp)
            )
            Spacer(Modifier.height(8.dp))
            Text("${profile.name} ${profile.surname}", fontSize = 20.sp)
            Spacer(Modifier.height(38.dp))
            Card(
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFF7F7F9)
                )
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text("Открыть", modifier = Modifier.rotate(-90f))
                    Column(
                        Modifier.padding(8.dp)
                    ) {
                        Image(
                            painter = painterResource(R.drawable.shtrihcode),
                            null,
                            modifier = Modifier.size(height = 51.dp, width = 297.dp)
                        )
                    }

                }


            }

            LazyColumn {
                item {
                    Spacer(Modifier.height(19.dp))
                    Text("Имя", modifier = Modifier.fillMaxWidth(), fontSize = 16.sp)
                    Spacer(Modifier.height(19.dp))
                    TextField(
                        value = userName,
                        onValueChange = {
                            userName = it
                        },
                        shape = RoundedCornerShape(14.dp),
                        enabled = false,
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color(0xFFF7F7F9),
                            unfocusedContainerColor = Color(0xFFF7F7F9),
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledContainerColor = Color(0xFFF7F7F9),
                            disabledIndicatorColor = Color.Transparent,

                        ),
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                item {
                    Spacer(Modifier.height(19.dp))
                    Text("Фамилия", modifier = Modifier.fillMaxWidth(), fontSize = 16.sp)
                    Spacer(Modifier.height(19.dp))
                    TextField(
                        value = userSurname,
                        onValueChange = {
                            userSurname = it
                        },
                        enabled = false,
                        shape = RoundedCornerShape(14.dp),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color(0xFFF7F7F9),
                            unfocusedContainerColor = Color(0xFFF7F7F9),
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledContainerColor = Color(0xFFF7F7F9),
                            disabledIndicatorColor = Color.Transparent
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                item {
                    Spacer(Modifier.height(19.dp))
                    Text("Адрес", modifier = Modifier.fillMaxWidth(), fontSize = 16.sp)
                    Spacer(Modifier.height(19.dp))
                    TextField(
                        value = userAddress ?: "",
                        onValueChange = {
                            userAddress = it
                        },
                        enabled = false,
                        shape = RoundedCornerShape(14.dp),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color(0xFFF7F7F9),
                            unfocusedContainerColor = Color(0xFFF7F7F9),
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledContainerColor = Color(0xFFF7F7F9),
                            disabledIndicatorColor = Color.Transparent
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )

                }
                item {
                    Spacer(Modifier.height(19.dp))
                    Text("Телефон", modifier = Modifier.fillMaxWidth(), fontSize = 16.sp)
                    Spacer(Modifier.height(19.dp))
                    TextField(
                        value = userPhone ?: "",
                        onValueChange = {
                            userPhone = it
                        },
                        shape = RoundedCornerShape(14.dp),
                        enabled = false,
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color(0xFFF7F7F9),
                            unfocusedContainerColor = Color(0xFFF7F7F9),
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledContainerColor = Color(0xFFF7F7F9),
                            disabledIndicatorColor = Color.Transparent,
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                item {
                    Spacer(Modifier.height(80.dp))
                }
            }
        }

    }
}
