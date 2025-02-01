package com.example.myapplication.presentation.editProfile

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import coil3.compose.rememberAsyncImagePainter
import com.example.myapplication.R
import com.example.myapplication.data.EmailManager
import com.example.myapplication.data.app.App
import com.example.myapplication.domain.models.Profile
import com.example.myapplication.presentation.editProfile.vm.EditProfileViewModel

@Composable
fun EditProfileScreen(editProfileViewModel: EditProfileViewModel, navController: NavController) {

    val isShow by editProfileViewModel.isShow.collectAsState()

    val email = EmailManager(LocalContext.current).get()

    LaunchedEffect(Unit) {
        editProfileViewModel.getProfile(email)
    }
    val profile by editProfileViewModel.profile.collectAsState()

    val userImage by editProfileViewModel.userImage.collectAsState()

    Log.d("chkeckasdlkf", profile.toString())

    var userName by remember { mutableStateOf("") }
    userName = profile.name

    var userSurname by remember { mutableStateOf("") }
    userSurname = profile.surname ?: ""

    var userAddress by remember { mutableStateOf(profile.address) }
    userAddress = profile.address ?: ""

    var userPhone by remember { mutableStateOf(profile.phone) }
    userPhone = profile.phone ?: ""

    //forImagePicker
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }

    // Логика для выбора изображения
    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri: Uri? ->
            selectedImageUri = uri
        }
    )


    Column(
        Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (isShow) {
            CircularProgressIndicator(
                modifier = Modifier.size(1.dp)
            )
        }
        Button(
            modifier = Modifier
                .width(212.dp)
                .height(32.dp),
            onClick = {
                editProfileViewModel.updateProfile(
                    Profile(
                        name = userName,
                        surname = userSurname,
                        address = userAddress,
                        phone = userPhone,
                        email = email
                    )
                )
                navController.navigate("profile")
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF48B2E7)
            ),
        ) {
            Text("Сохранить")
        }
        Spacer(Modifier.height(48.dp))
        Column(
            Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            selectedImageUri?.let {
                Card(
                    shape = RoundedCornerShape(100)
                ) {
                    AsyncImage(
                        model = userImage,
                        contentDescription = "Selected Image",
                        modifier = Modifier.size(96.dp)
                    )
                }
                editProfileViewModel.uploadImage(App.userId, it, context)

            }
            Spacer(Modifier.height(8.dp))
            Text("${profile.name} ${profile.surname}", fontSize = 20.sp)
            Spacer(Modifier.height(11.dp))
            Text("Изменить фото профиля", color = Color(0xFF48B2E7), modifier = Modifier.clickable {
                launcher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                }
            )

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
                        trailingIcon = {
                            Icon(
                                painterResource(R.drawable.applyicon),
                                null,
                                tint = Color.Unspecified
                            )
                        },
                        shape = RoundedCornerShape(14.dp),
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
                        trailingIcon = {
                            Icon(
                                painterResource(R.drawable.applyicon),
                                null,
                                tint = Color.Unspecified
                            )
                        },
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
                        shape = RoundedCornerShape(14.dp),
                        trailingIcon = {
                            Icon(
                                painterResource(R.drawable.applyicon),
                                null,
                                tint = Color.Unspecified
                            )
                        },
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
                        trailingIcon = {
                            Icon(
                                painterResource(R.drawable.applyicon),
                                null,
                                tint = Color.Unspecified
                            )
                        },
                        shape = RoundedCornerShape(14.dp),
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

