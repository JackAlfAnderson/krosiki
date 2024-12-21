package com.example.myapplication.presentation.otpVerification

import android.os.CountDownTimer
import android.util.Log
import android.widget.Toast
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
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.R
import com.example.myapplication.data.EmailManager
import com.example.myapplication.presentation.newPassword.NewPasswordScreen
import com.example.myapplication.presentation.newPassword.vm.NewPasswordViewModel
import com.example.myapplication.presentation.otpVerification.vm.OtpVerificationViewModel
import com.example.myapplication.ui.theme.LightGrayCustomSuperMega
import kotlinx.coroutines.delay
import kotlin.math.sin
import kotlin.time.Duration.Companion.seconds

@Composable
fun OtpVerificationScreen(navController: NavController, otpVerificationViewModel: OtpVerificationViewModel, newPasswordViewModel: NewPasswordViewModel) {


    //Circular indication
    val isShow by otpVerificationViewModel.isShow.collectAsState()

    //NewPassShow
    var isNewPasswordShow by remember { mutableStateOf(false) }

    //Email
    val context = LocalContext.current
    val emailManager = EmailManager(context)

    //OTP
    var otp1 by remember { mutableStateOf("") }
    var otp2 by remember { mutableStateOf("") }
    var otp3 by remember { mutableStateOf("") }
    var otp4 by remember { mutableStateOf("") }
    var otp5 by remember { mutableStateOf("") }
    var otp6 by remember { mutableStateOf("") }

    //OTPFocus
    val (item1 ,item2, item3, item4, item5, item6) = FocusRequester.createRefs()

    //Timer
    var isTextEnabled by remember { mutableStateOf(false) }
    var time by remember { mutableStateOf("") }
    var ticks by remember { mutableStateOf(10) }
    LaunchedEffect(Unit) {
        while (true) {
            delay(1.seconds)
            ticks--
            if (ticks <= 0){
                isTextEnabled = true
            } else {
                isTextEnabled = false
            }
        }
    }



    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
        ){
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
                    navController.navigate("pager")
                }
            )
        }
        Spacer(Modifier.height(11.dp))
        Column(modifier = Modifier.padding(horizontal = 20.dp)) {
            Text("OTP Проверка", fontSize = 32.sp, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
            Spacer(Modifier.height(8.dp))
            Text("Пожалуйста, проверьте свою электронную почту, чтобы увидеть код подтверждения", fontSize = 16.sp, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
            Spacer(Modifier.height(16.dp))
            Text("OTP Код", fontSize = 21.sp)
            Spacer(Modifier.height(20.dp))
        }

        Row (Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
            ){
            CustomOtpItem(
                text = otp1,
                onValueChanged = {
                    otp1 = it
                },
                modifier = Modifier
                    .focusRequester(item1)
                    .focusProperties {
                        next = item2
                        previous = item1
                    }
            )
            CustomOtpItem(
                text = otp2,
                onValueChanged = {
                    otp2 = it
                },
                modifier = Modifier
                    .focusRequester(item2)
                    .focusProperties {
                        next = item3
                        previous = item1
                    }
            )
            CustomOtpItem(
                text = otp3,
                onValueChanged = {
                    otp3 = it
                },
                modifier = Modifier
                    .focusRequester(item3)
                    .focusProperties {
                        next = item4
                        previous = item2
                    }
            )
            CustomOtpItem(
                text = otp4,
                onValueChanged = {
                    otp4 = it
                },
                modifier = Modifier
                    .focusRequester(item4)
                    .focusProperties {
                        next = item5
                        previous = item3
                    }
            )
            CustomOtpItem(
                text = otp5,
                onValueChanged = {
                    otp5 = it
                },
                modifier = Modifier
                    .focusRequester(item5)
                    .focusProperties {
                        next = item6
                        previous = item4
                    }
            )
            CustomOtpItem(
                text = otp6,
                onValueChanged = {
                    otp6 = it
                    if (otp6 != null){
                        val allOtp = (otp1+otp2+otp3+otp4+otp5+otp6)
                        otpVerificationViewModel.verifyOtp(email = emailManager.get(), token = allOtp)
                        Log.d("OTP", allOtp)
                        isNewPasswordShow = true
                    }

                },
                modifier = Modifier
                    .focusRequester(item6)
                    .focusProperties {
                        previous = item5
                    },
                moveFocus = false
            )
            if (isNewPasswordShow){
                NewPasswordScreen(newPasswordViewModel, navController)
            }

        }

        Spacer(Modifier.height(20.dp))
        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)) {

            Text("Отправить заново", modifier = Modifier
                .clickable(enabled = isTextEnabled){
                    ticks = 10
                }
            )

            Row (Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End){
                val showTimer = if (ticks <= 0){
                    "00:00"
                } else {
                    "00:" + if (ticks < 10){
                        "0" + ticks
                    } else{
                        ticks
                    }
                }
                Text(showTimer, fontSize = 12.sp)
            }


        }
    }
}


@Composable
fun CustomOtpItem(
    modifier: Modifier = Modifier,
    text: String,
    onValueChanged: (String) -> Unit,
    moveFocus: Boolean = true
) {
    val focusManager = LocalFocusManager.current
    var textChange by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier.padding(horizontal = 8.dp)
    ) {
        TextField(
            modifier = Modifier
                .width(46.dp)
                .height(99.dp)
                .border(
                    width = 1.dp,
                    shape = RoundedCornerShape(12.dp),
                    brush = Brush.horizontalGradient(
                        if (textChange.isNotEmpty()) listOf(
                            Color.Transparent,
                            Color.Transparent
                        ) else listOf(Color(0xFFF87265), Color(0xFFF87265))
                    )
                )
                .onKeyEvent {
                    if (textChange.isNotEmpty() && moveFocus) {
                        focusManager.moveFocus(focusDirection = FocusDirection.Next)
                        true
                    }

                    if (text.isEmpty() && it.key == Key.Backspace) {
                        focusManager.moveFocus(FocusDirection.Previous)
                    }
                    false
                },
            textStyle = TextStyle(color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 18.sp),
            value = text,
            onValueChange = {
                if (it.length <= 1) {
                    onValueChanged.invoke(it)
                    textChange = it
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            shape = RoundedCornerShape(12.dp),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                unfocusedContainerColor = LightGrayCustomSuperMega,
                focusedContainerColor = LightGrayCustomSuperMega
            ),
            singleLine = true
        )
    }
}
