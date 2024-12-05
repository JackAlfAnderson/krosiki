package com.example.myapplication.presentation.signIn

import android.app.Dialog
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.R
import com.example.myapplication.presentation.signUp.CustomCheckBox
import com.example.myapplication.ui.theme.ButtonSuperColor
import com.example.myapplication.ui.theme.LightGrayCustomSuperMega
import com.example.myapplication.ui.theme.pinini

@Composable
fun SignInScreen(navController: NavController) {


    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(Modifier.height(23.dp))
        Column(Modifier.fillMaxWidth()) {
            Icon(
                painter = painterResource(R.drawable.back_icon),
                null,
                tint = Color.Unspecified,
                modifier = Modifier.clickable {
                    navController.navigate("pager")
                }
            )
        }
        Spacer(Modifier.height(11.dp))
        Text(
            "Привет!",
            fontSize = 32.sp
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = "Заполните Свои Данные Или Продолжите Через Социальные Медиа",
            fontSize = 16.sp,
            textAlign = TextAlign.Center,

            )
        Spacer(modifier = Modifier.height(35.dp))

        CustomTextField(
            titleText = "Email",
            text = email,
            onValueChanged = {
                email = it
            },
            label = "Email",
            trailingIcon = null,
            KeyboardType.Email,
            visualTransformation = false
        )
        Spacer(Modifier.height(26.dp))

        CustomTextField(
            titleText = "Пароль",
            text = password,
            onValueChanged = {
                password = it
            },
            label = "Пароль",
            trailingIcon = R.drawable.eye_slash,
            KeyboardType.Password,
            visualTransformation = true
        )
        Spacer(Modifier.height(12.dp))
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Text("Восстановить", Modifier.clickable {
                    navController.navigate("forgot")
                }
            )
        }
        Spacer(Modifier.height(24.dp))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            onClick = {
                navController.navigate("home")
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = ButtonSuperColor
            ),
            shape = RoundedCornerShape(14.dp)
        ) {
            Text(
                "Войти"
            )
        }

    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 50.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        Row {
            Text("Вы впервые? ", fontSize = 16.sp)
            Text(
                "Создать пользователя",
                fontSize = 16.sp,
                modifier = Modifier.clickable {
                    navController.navigate("signUp")
                }
            )
        }
    }
}

@Composable
fun CustomTextField(
    titleText: String,
    text: String,
    onValueChanged: (String) -> Unit,
    label: String,
    trailingIcon: Int?,
    keyboardType: KeyboardType,
    visualTransformation: Boolean
) {
    var hider by remember {
        mutableStateOf(visualTransformation)
    }
    Text(
        titleText,
        modifier = Modifier.fillMaxWidth()
    )
    Spacer(Modifier.height(12.dp))
    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = text,
        onValueChange = {
            onValueChanged(it)
        },
        label = {
            Text(
                label, color = Color(0xFF6A6A6A)
            )
        },
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        visualTransformation = if (hider) PasswordVisualTransformation() else VisualTransformation.None,
        shape = RoundedCornerShape(14.dp),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            unfocusedContainerColor = LightGrayCustomSuperMega,
        ),
        trailingIcon = {
            if (trailingIcon != null) {
                Icon(
                    modifier = Modifier.clickable {
                        hider = !hider
                    },
                    painter = painterResource(
                        id = trailingIcon
                    ),
                    contentDescription = null,
                    tint = Color.Unspecified
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun SignInScreenPreview() {
    SignInScreen(rememberNavController())
}