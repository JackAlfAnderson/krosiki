package com.example.myapplication.presentation.newPassword

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.data.EmailManager
import com.example.myapplication.data.app.App
import com.example.myapplication.presentation.newPassword.vm.NewPasswordViewModel

@Composable
fun NewPasswordScreen(newPasswordViewModel: NewPasswordViewModel, navController: NavController) {


    val isShow by newPasswordViewModel.isShow.collectAsState()
    val context = LocalContext.current
    var emailManager = EmailManager(context)
    var newPassword by remember { mutableStateOf("") }

    Dialog(
        onDismissRequest = {

        }
    ) {
        if (isShow) {
            CircularProgressIndicator(
                modifier = Modifier.size(1.dp)
            )
        }
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )) {
            Column(
                Modifier.padding(40.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                     "Вы успешно подтвердили OTP!"
                )
                Spacer(Modifier.height(20.dp))
                Text(
                    "Введите новый пароль:"
                )
                Spacer(Modifier.height(30.dp))
                TextField(
                    value = newPassword,
                    onValueChange = {
                        newPassword = it
                    },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color(0xFFF7F7F9),
                        unfocusedContainerColor = Color(0xFFF7F7F9),
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent
                    ),
                    shape = RoundedCornerShape(14.dp),
                    label = {
                        Text(
                            if(newPassword.isEmpty()) "newPassword" else "", color = Color(0xFF6A6A6A)
                        )
                    }
                )
                Spacer(Modifier.height(30.dp))
                Button(
                    onClick = {
                        newPasswordViewModel.newPassword(email = emailManager.get(), password = newPassword)
                        navController.navigate("home")
                    },
                    enabled = newPassword.length > 6,
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF48B2E7)
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                ) {
                    Text("Подтвердить")
                }
            }
        }
    }
}
