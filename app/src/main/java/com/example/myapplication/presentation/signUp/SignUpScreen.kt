package com.example.myapplication.presentation.signUp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.materialIcon
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.R
import com.example.myapplication.presentation.signIn.CustomTextField
import com.example.myapplication.ui.theme.ButtonSuperColor

@Composable
fun SignUpScreen(navController: NavController) {

    var userName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isChecked by remember { mutableStateOf(true) }

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
                    navController.navigate("signIn")
                }
            )
        }
        Spacer(Modifier.height(11.dp))
        Text(
            "Регистрация",
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
            titleText = "Ваше имя",
            text = userName,
            onValueChanged = {
                userName = it
            },
            label = "х х х х х х х х",
            trailingIcon = null,
            KeyboardType.Email,
            visualTransformation = false
        )
        Spacer(Modifier.height(26.dp))

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
        Spacer(Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CustomCheckBox(onCheckedChange = {
                isChecked = it
            })
            Spacer(Modifier.width(12.dp))
            Text("Даю согласие на обработку персональных данных")

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
            Text("Есть аккаунт? ", fontSize = 16.sp)
            Text(
                "Войти",
                fontSize = 16.sp,
                modifier = Modifier.clickable {
                    navController.navigate("signIn")
                }
            )
        }
    }
}

@Composable
fun CustomCheckBox(onCheckedChange: (Boolean) -> Unit) {

    var isChecked by remember { mutableStateOf(true) }
    Card(
        modifier = Modifier
            .clip(RoundedCornerShape(6.dp))
            .size(18.dp)
            .clickable {
                isChecked = !isChecked
            },
        shape = RoundedCornerShape(6.dp)
    ) {
        Column(
            Modifier.fillMaxSize().background(Color(0xFFF7F7F9)),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (isChecked) {
                Icon(painterResource(R.drawable.checkboxicon), null, tint = Color.Unspecified)

                onCheckedChange(isChecked)
            }
        }
    }
}

@Preview
@Composable
private fun PreviewCustom() {
    CustomCheckBox(onCheckedChange = {})
}

@Preview(showBackground = true)
@Composable
private fun SignUpScreenPreview() {
    SignUpScreen(rememberNavController())
}