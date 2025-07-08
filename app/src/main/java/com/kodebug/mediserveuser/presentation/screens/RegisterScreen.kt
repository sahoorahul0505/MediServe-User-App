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

    LaunchedEffect(Unit) {
        viewModel.createUiEvent.collect { event ->
            when (event) {
                is CreateUiEvent.ShowToast -> Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show() // this message will come from the ViewModel
                is CreateUiEvent.Navigate -> navController.navigate(event.route) {
                    popUpTo(Routes.RegisterScreenRoute) {
                        inclusive = true
                    }
                }
            }
        }
    }

    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Registration Screen")
            Spacer(modifier = modifier.height(30.dp))
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text(text = "Name") }
            )
            Spacer(modifier = modifier.height(30.dp))
            OutlinedTextField(
                value = email,
                onValueChange = { email = it},
                label = { Text(text = "Email") }
            )
            Spacer(modifier = modifier.height(30.dp))
            OutlinedTextField(
                value = phoneNumber,
                onValueChange = { phoneNumber = it},
                label = { Text(text = "Phone number") }
            )
            Spacer(modifier = modifier.height(30.dp))
            OutlinedTextField(
                value = password,
                onValueChange = { password = it},
                label = { Text(text = "password") }
            )
            Spacer(modifier = modifier.height(30.dp))
            OutlinedTextField(
                value = address,
                onValueChange = { address = it},
                label = { Text(text = "address") }
            )
            Spacer(modifier = modifier.height(30.dp))
            OutlinedTextField(
                value = pinCode,
                onValueChange = { pinCode = it},
                label = { Text(text = "pinCode") }
            )
            Spacer(modifier = modifier.height(30.dp))
            ElevatedButton(
                onClick = {
                    if (name.isEmpty() && phoneNumber.isEmpty() && email.isEmpty() && password.isEmpty()){
                        return@ElevatedButton
                    Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
                } else{
                    viewModel.createUser(name, phoneNumber, email, password, address, pinCode)
                }
                },
                enabled = name.isNotEmpty() && phoneNumber.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()
            ) {
                if (uiState.isLoading) {
                    CircularProgressIndicator(modifier = modifier.size(20.dp))
                } else {
                    Text(text = "Register")
                }

            }

            if (uiState.error.isNullOrEmpty().not()){ // this error will co=ame from the userRepo
                Spacer(modifier = modifier.height(18.dp))
                Text(text = uiState.error.toString(), color = Color.Red)
            }

            Spacer(modifier = modifier.height(30.dp))
            Text(text = "Click here to login", color = Color.Blue, modifier = modifier.clickable(
                onClick = {
                    navController.navigate(Routes.LoginScreenRoute){
                        popUpTo(Routes.RegisterScreenRoute){
                            inclusive = true
                        }
                    }
                }
            ))
        }
    }
}