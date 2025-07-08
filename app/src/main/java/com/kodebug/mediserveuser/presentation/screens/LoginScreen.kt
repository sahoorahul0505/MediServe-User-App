package com.kodebug.mediserveuser.presentation.screens

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.kodebug.mediserveuser.presentation.navigation.Routes
import com.kodebug.mediserveuser.viewmodel.userViewModel.AuthViewModel
import com.kodebug.mediserveuser.viewmodel.userViewModel.stateAndEvent.LoginUiEvent

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: AuthViewModel = hiltViewModel()
) {

    val uiState by viewModel.loginUiState.collectAsState()
    val context = LocalContext.current
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        viewModel.loginUiEvent.collect { event ->
            when (event) {
                is LoginUiEvent.Navigate -> navController.navigate(event.route){
                    popUpTo(Routes.LoginScreenRoute){
                        inclusive = true
                    }
                }
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
            Text(text = "Login Screen")
            Spacer(modifier = modifier.height(30.dp))
            OutlinedTextField(
                value = email,
                onValueChange = { email = it},
                label = { Text(text = "Email") }
            )
            Spacer(modifier = modifier.height(30.dp))
            OutlinedTextField(
                value = password,
                onValueChange = { password = it},
                label = { Text(text = "Password") }
            )
            Spacer(modifier = modifier.height(30.dp))
            ElevatedButton(
                onClick = {
                    if (email.isEmpty() && password.isEmpty()){
                        return@ElevatedButton
                        Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
                    } else{
                        viewModel.loginUser(email, password)
                    }
                }
            ){
                if (uiState.isLoading){
                    CircularProgressIndicator(modifier = modifier.size(20.dp))
                } else{
                    Text(text = "Login")
                }
            }

            Spacer(modifier = modifier.height(30.dp))
            if (uiState.error.isNullOrEmpty().not()){
                Text(text = uiState.error.toString())
            }

            Spacer(modifier = modifier.height(30.dp))
            Text(text = "Click here to register", color = Color.Blue, modifier = modifier.clickable(
                onClick = {
                    navController.navigate(Routes.RegisterScreenRoute){
                        popUpTo(Routes.LoginScreenRoute){
                            inclusive = true
                        }
                    }
                }
            ))
        }
    }
}