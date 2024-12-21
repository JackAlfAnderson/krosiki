package com.example.myapplication.presentation.forgotPassword

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
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
import com.example.myapplication.data.EmailManager
import com.example.myapplication.presentation.forgotPassword.VM.ForgotPasswordViewModel
import com.example.myapplication.presentation.signIn.CustomTextField
import com.example.myapplication.ui.theme.ButtonSuperColor
import com.example.myapplication.ui.theme.LightGrayCustomSuperMega

@Composable
fun ForgotPasswordScreen(navController: NavController, forgotPasswordViewModel: ForgotPasswordViewModel) {


    val isShow by forgotPasswordViewModel.isShow.collectAsState()
    val context = LocalContext.current
    var email by remember { mutableStateOf("") }
    val emailManager = EmailManager(context)
    var showDialog by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        if (isShow) {
            CircularProgressIndicator(
                modifier = Modifier.size(1.dp)
            )
        }
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
            "Забыл пароль",
            fontSize = 32.sp
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = "Введите свою учетную запись\n" +
                    " для сброса",
            fontSize = 16.sp,
            textAlign = TextAlign.Center,

            )
        Spacer(modifier = Modifier.height(30.dp))
        CustomTextField(
            titleText = "",
            text = email,
            onValueChanged = {
                email = it
            },
            label = "xyz@gmail.com",
            trailingIcon = null,
            KeyboardType.Email,
            visualTransformation = false
        )
        Spacer(Modifier.height(24.dp))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            onClick = {
                forgotPasswordViewModel.sendOTP(email = email)
                emailManager.set(email)
                showDialog = true
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = ButtonSuperColor
            ),
            shape = RoundedCornerShape(14.dp)
        ) {
            Text(
                "Отправить"
            )
        }
    }
    CustomEmailDialog(
        isShow = showDialog,
        onDismissRequest =  {
            showDialog = false
            navController.navigate("otpVer")
        },
        onNextPage = {
            navController.navigate("otpVer")
        }
    )
}

@Composable
fun CustomEmailDialog(
    isShow: Boolean,
    onDismissRequest: () -> Unit,
    onNextPage: () -> Unit
) {
    if (isShow) {
        Dialog(
            onDismissRequest = {
                onDismissRequest()
            }
        ) {
            Card(
                modifier = Modifier.clickable {
                    onNextPage()
                },
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(
                        start = 20.dp,
                        top = 30.dp,
                        end = 20.dp,
                        bottom = 30.dp
                    )
                ) {
                    Icon(painter = painterResource(R.drawable.emailicon), null, tint = Color.Unspecified)
                    Spacer(Modifier.height(24.dp))
                    Text(
                        "Проверьте Ваш Email",
                    )
                    Spacer(Modifier.height(8.dp))
                    Text("Мы отправили код восстановления", color = Color(0xFF707B81), textAlign = TextAlign.Center)
                    Text("пароля на вашу электронную почту.", color = Color(0xFF707B81), textAlign = TextAlign.Center)
                }
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CusEmDial() {
    CustomEmailDialog(
        onDismissRequest = {},
        onNextPage = {},
        isShow = true
    )
}
