package com.kodebug.mediserveuser.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.kodebug.mediserveuser.presentation.navigation.Routes

@Composable
fun HomeScreen(modifier: Modifier = Modifier, navController: NavController) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            modifier = modifier.fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Home Screen")
            Spacer(modifier = modifier.height(30.dp))
            Button(
                onClick = {
                    navController.navigate(Routes.ProfileScreenRoute){
                        popUpTo(Routes.HomeScreenRoute){
                            inclusive = true
                        }
                    }
                }
            ) {
                Text(text = "Profile Page")
            }
        }
    }
}