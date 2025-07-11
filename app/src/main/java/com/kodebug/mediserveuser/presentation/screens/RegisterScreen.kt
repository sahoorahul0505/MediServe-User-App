package com.kodebug.mediserveuser.presentation.screens

import android.widget.Toast
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.kodebug.mediserveuser.R
import com.kodebug.mediserveuser.presentation.components.CustomButton
import com.kodebug.mediserveuser.presentation.components.CustomTextField
import com.kodebug.mediserveuser.presentation.navigation.Routes
import com.kodebug.mediserveuser.ui.theme.darkGreen
import com.kodebug.mediserveuser.ui.theme.darkGreen2
import com.kodebug.mediserveuser.ui.theme.latoFont
import com.kodebug.mediserveuser.ui.theme.madimiOneFont
import com.kodebug.mediserveuser.ui.theme.poppinsFont
import com.kodebug.mediserveuser.ui.theme.softGreen2
import com.kodebug.mediserveuser.ui.theme.whiteGreen
import com.kodebug.mediserveuser.viewmodel.userViewModel.AuthViewModel
import com.kodebug.mediserveuser.viewmodel.userViewModel.stateAndEvent.CreateUiEvent

@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: AuthViewModel = hiltViewModel()
) {
    val uiState by viewModel.createUiState.collectAsState()
    val context = LocalContext.current


    var name by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var pinCode by remember { mutableStateOf("") }

//    LaunchedEffect(Unit) {
//        viewModel.createUiEvent.collect { event ->
//            when (event) {
//                is CreateUiEvent.ShowToast -> Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show() // this message will come from the ViewModel
//                is CreateUiEvent.Navigate -> navController.navigate(event.route) {
//                    popUpTo(Routes.RegisterScreenRoute) {
//                        inclusive = true
//                    }
//                }
//            }
//        }
//    }

    Box(modifier = modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.back_green_element),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier
                .size(650.dp)
                .offset((240).dp, (-300).dp)
                .rotate(110f)
        )
        Image(
            painter = painterResource(id = R.drawable.back_green_element),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier
                .size(650.dp)
                .offset((-210).dp, (620).dp)
                .rotate(100f)
        )
        Image(
            painter = painterResource(id = R.drawable.sign_up_form),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier
                .size(250.dp)
                .align(alignment = Alignment.TopCenter)
                .offset(y = (-30).dp)
        )
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(top = 160.dp, start = 34.dp, end = 34.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Create account",
                fontSize = 38.sp,
                textAlign = TextAlign.Center,
                fontFamily = madimiOneFont,
                color = darkGreen2,
                modifier = modifier.fillMaxWidth()
            )
            Text(
                text = "To get benefits of our service.",
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                fontFamily = latoFont,
                letterSpacing = 2.sp,
                color = darkGreen,
                modifier = modifier.fillMaxWidth()
            )
            Spacer(modifier = modifier.height(6.dp))
            CustomTextField(
                value = name,
                onValueChange = { name = it },
                label = "Enter your name",
            )
            Spacer(modifier = modifier.height(16.dp))
            CustomTextField(
                value = email,
                onValueChange = { email = it },
                label = "Enter your email",
            )
            Spacer(modifier = modifier.height(16.dp))
            CustomTextField(
                value = phoneNumber,
                onValueChange = { phoneNumber = it },
                label = "Enter your phone number"
            )
            Spacer(modifier = modifier.height(16.dp))
            CustomTextField(
                value = password,
                onValueChange = { password = it },
                label = "Create password"
            )
            Spacer(modifier = modifier.height(16.dp))
            CustomTextField(
                value = address,
                onValueChange = { address = it },
                label = "Enter your address"
            )
            Spacer(modifier = modifier.height(16.dp))
            CustomTextField(
                value = pinCode,
                onValueChange = { pinCode = it },
                label = "Enter your area pin code"
            )
            Spacer(modifier = modifier.height(34.dp))
            if (uiState.error.isNullOrEmpty().not()) { // this error will co=ame from the userRepo
                Text(text = uiState.error.toString(), color = Color.Red)
                Spacer(modifier = modifier.height(8.dp))
            }
            OutlinedButton(
                onClick = {
//                    if (name.isEmpty() && phoneNumber.isEmpty() && email.isEmpty() && password.isEmpty()) {
//                        return@CustomButton
//                        Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
//                    } else {
//                        viewModel.createUser(name, phoneNumber, email, password, address, pinCode)
//                    }
                },
                enabled = name.isNotEmpty() && phoneNumber.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = softGreen2.copy(alpha = 0.7f),
                    disabledContainerColor = Color.LightGray.copy(alpha = .6f),
                    disabledContentColor = darkGreen.copy(alpha = .5f),
                    contentColor = darkGreen2
                ),
                border = ButtonDefaults.outlinedButtonBorder.copy(
                    width = 2.dp,
                    brush = Brush.verticalGradient(listOf(whiteGreen, whiteGreen))
                ),
                elevation = ButtonDefaults.elevatedButtonElevation(
                    defaultElevation = 8.dp,
                    disabledElevation = 5.dp,
                    focusedElevation = 8.dp
                ),
                modifier = Modifier
                    .fillMaxWidth(.7f)
                    .height(64.dp)
            ) {
                if (uiState.isLoading) {
                    CircularProgressIndicator(
                        modifier = modifier
                            .size(50.dp)
                            .width(10.dp), color = softGreen2
                    )
                } else {
                    val offset = Offset(5.0f, 8.0f)
                    Text(
                        text = "Register",
                        style = TextStyle(
                            fontSize = 38.sp,
                            fontFamily = madimiOneFont,
                            shadow = Shadow(
                                color = darkGreen2.copy(alpha = .3f), offset = offset, blurRadius = 10f
                            )
                        )
                    )
                }
            }
            Spacer(modifier = modifier.height(12.dp))
            Row(modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Text(
                    text = "By continuing you are agreeing our ",
                    style = TextStyle(
                        fontSize = 11.sp,
                        color = darkGreen2,
                        fontFamily = poppinsFont
                    )
                )
                Text(
                    text = "terms & conditions ",
                    style = TextStyle(
                        textDecoration = TextDecoration.Underline,
                        fontSize = 11.sp,
                        color = Color(0xFf48cae4),
                        fontFamily = poppinsFont
                    )
                )
                Text(
                    text = "and our privacy polices",
                    style = TextStyle(
                        fontSize = 11.sp,
                        color = darkGreen2,
                        fontFamily = poppinsFont
                    )
                )
            }

            Spacer(modifier = modifier.height(30.dp))
            Row(modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Text(
                    text = "Already have an account?",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontFamily = poppinsFont,
                        color = darkGreen2
                    )
                )
                Spacer(modifier = modifier.width(3.dp))
                Text(
                    text = "Login",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontFamily = poppinsFont,
                        color = Color(0xFf48cae4)
                    ),
                    modifier = modifier.clickable(
                        onClick = {
                            navController.navigate(Routes.LoginScreenRoute) {
                                popUpTo(Routes.RegisterScreenRoute) {
                                    inclusive = true
                                }
                            }
                        }
                    )
                )
            }
        }
    }
}