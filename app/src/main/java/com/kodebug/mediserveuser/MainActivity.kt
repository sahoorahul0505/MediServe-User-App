package com.kodebug.mediserveuser

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.kodebug.mediserveuser.presentation.navigation.AppNavigation
import com.kodebug.mediserveuser.presentation.screens.RegisterScreen
import com.kodebug.mediserveuser.presentation.screens.SplashScreen
import com.kodebug.mediserveuser.ui.theme.MediServeUserTheme
import com.kodebug.mediserveuser.ui.theme.brush
import com.kodebug.mediserveuser.ui.theme.whiteGreen
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MediServeUserTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(modifier = Modifier
                        .fillMaxSize()
                        .background(brush = brush)
                        .padding(innerPadding)) {
//                        AppNavigation()
//                        SplashScreen(navController = rememberNavController())
                        RegisterScreen(navController = rememberNavController())
                    }
                }
            }
        }
    }
}
