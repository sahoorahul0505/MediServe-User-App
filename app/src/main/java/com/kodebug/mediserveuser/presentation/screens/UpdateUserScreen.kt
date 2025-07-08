package com.kodebug.mediserveuser.presentation.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.kodebug.mediserveuser.presentation.navigation.Routes
import com.kodebug.mediserveuser.viewmodel.userViewModel.UpdateUserViewModel
import com.kodebug.mediserveuser.viewmodel.userViewModel.stateAndEvent.UpdateUserUiEvent

@Composable
fun UpdateUserScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: UpdateUserViewModel = hiltViewModel()
) {

    val uiState by viewModel.updateUiState.collectAsState()
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        viewModel.updateUiEvent.collect { event ->
            when (event) {
                is UpdateUserUiEvent.Navigate -> {
                    navController.navigate(event.route)
                    {
                        popUpTo(Routes.UpdateUserScreenRoute) {
                            inclusive = true
                        }
                        popUpTo(Routes.ProfileScreenRoute){
                            inclusive = true
                        }
//                        popUpTo(navController.graph.startDestinationId)
//                        launchSingleTop = true
                    }
                }

                is UpdateUserUiEvent.ShowToast -> {
                    Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        if (uiState.isLoading) {
            CircularProgressIndicator(modifier = modifier.align(Alignment.Center))
        } else {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(20.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Edit profile", fontSize = 30.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = modifier.height(20.dp))
                OutlinedTextField(
                    value = uiState.name,
                    onValueChange = {
                        viewModel.onFieldChange(field = "name", value = it)
                    },
                    label = { Text(text = "Name") },
                    modifier = modifier.fillMaxWidth()
                )
                Spacer(modifier = modifier.height(20.dp))
                OutlinedTextField(
                    value = uiState.email,
                    onValueChange = {
                        viewModel.onFieldChange(field = "email", value = it)
                    },
                    label = { Text(text = "Email") },
                    modifier = modifier.fillMaxWidth()
                )
                Spacer(modifier = modifier.height(20.dp))
                OutlinedTextField(
                    value = uiState.phoneNumber,
                    onValueChange = {
                        viewModel.onFieldChange(field = "phoneNumber", value = it)
                    },
                    label = { Text(text = "Phone number") },
                    modifier = modifier.fillMaxWidth()
                )
                Spacer(modifier = modifier.height(20.dp))
                OutlinedTextField(
                    value = uiState.address,
                    onValueChange = {
                        viewModel.onFieldChange(field = "address", value = it)
                    },
                    label = { Text(text = "Address") },
                    modifier = modifier.fillMaxWidth()
                )
                Spacer(modifier = modifier.height(20.dp))
                OutlinedTextField(
                    value = uiState.pinCode,
                    onValueChange = {
                        viewModel.onFieldChange(field = "pinCode", value = it)
                    },
                    label = { Text(text = "Pin code") },
                    modifier = modifier.fillMaxWidth()
                )
                Spacer(modifier = modifier.height(20.dp))
                OutlinedTextField(
                    value = uiState.password,
                    onValueChange = {
                        viewModel.onFieldChange(field = "password", value = it)
                    },
                    label = { Text(text = "Password") },
                    modifier = modifier.fillMaxWidth()
                )
                Spacer(modifier = modifier.height(20.dp))
                Row(modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
                    ElevatedButton(
                        onClick = {
                            navController.navigate(Routes.ProfileScreenRoute){
                                popUpTo(Routes.UpdateUserScreenRoute){
                                    inclusive = true
                                }
                                popUpTo(Routes.ProfileScreenRoute){
                                    inclusive = true
                                }
                            }
                        }
                    ) {
                        Text(text = "Cancel")
                    }
                    ElevatedButton(
                        onClick = {
                            viewModel.updateUser()
                        }
                    ) {
                        Text(text = "Save changes")
                    }
                }
            }
        }
    }
}