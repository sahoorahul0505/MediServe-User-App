package com.kodebug.mediserveuser.presentation.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.kodebug.mediserveuser.presentation.navigation.Routes
import com.kodebug.mediserveuser.viewmodel.userViewModel.UserViewModel
import com.kodebug.mediserveuser.viewmodel.userViewModel.stateAndEvent.LoginUiEvent

@Composable
fun WaitingScreen(modifier: Modifier = Modifier, userId: String, navController: NavController, viewModel: UserViewModel = hiltViewModel()) {

    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.loginUiEvent.collect { event ->
            when (event) {
                is LoginUiEvent.Navigate -> navController.navigate(event.route)
                is LoginUiEvent.ShowToast -> Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Wait for approval")
            Spacer(modifier = modifier.height(50.dp))
            Text(text = "Have another account \n click here to login", textAlign = TextAlign.Center)
            Spacer(modifier = modifier.height(50.dp))
            ElevatedButton(
                onClick = {
                    viewModel.logOut()
                    navController.navigate(Routes.LoginScreenRoute)
                }
            ) {
                Text(text = "Login with another account")
            }
        }
    }
}