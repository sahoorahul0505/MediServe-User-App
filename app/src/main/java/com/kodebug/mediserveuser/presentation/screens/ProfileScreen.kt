package com.kodebug.mediserveuser.presentation.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.kodebug.mediserveuser.presentation.components.ProfileCard
import com.kodebug.mediserveuser.presentation.navigation.Routes
import com.kodebug.mediserveuser.viewmodel.userViewModel.ProfileViewModel
import com.kodebug.mediserveuser.viewmodel.userViewModel.UserViewModel
import com.kodebug.mediserveuser.viewmodel.userViewModel.stateAndEvent.UserUiEvent

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val uiState by viewModel.userState.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.userUiEvent.collect { event ->
            when (event) {
                is UserUiEvent.Navigate -> navController.navigate(event.route)
                is UserUiEvent.ShowToast -> Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            when {
                uiState.isLoading -> {
                    LinearProgressIndicator(modifier = modifier
                        .fillMaxWidth()
                        .size(30.dp))
                }

                uiState.error != null -> {
                    Text(text = "Error: ${uiState.error}")
                }

                uiState.user != null -> {
                    ProfileCard(userData = uiState.user!!)
                    Button(
                        onClick = {
                            navController.navigate(Routes.HomeScreenRoute) {
                                popUpTo(Routes.ProfileScreenRoute) {
                                    inclusive = true
                                }
                            }
                        }
                    ) {
                        Text(text = "Back")
                    }
                    Spacer(modifier = modifier.height(20.dp))
                    Button(
                        onClick = {
                            viewModel.logOut()
                        }
                    ) {
                        Text(text = "LogOut")
                    }
                }
            }
        }
    }
}