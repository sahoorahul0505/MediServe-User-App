package com.kodebug.mediserveuser.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.kodebug.mediserveuser.presentation.screens.HomeScreen
import com.kodebug.mediserveuser.presentation.screens.LoginScreen
import com.kodebug.mediserveuser.presentation.screens.ProfileScreen
import com.kodebug.mediserveuser.presentation.screens.RegisterScreen
import com.kodebug.mediserveuser.presentation.screens.SplashScreen
import com.kodebug.mediserveuser.presentation.screens.UpdateUserScreen
import com.kodebug.mediserveuser.presentation.screens.WaitingScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.SplashScreenRoute) {

        composable<Routes.SplashScreenRoute>{
            SplashScreen(navController = navController)
        }


        composable<Routes.RegisterScreenRoute> {
            RegisterScreen(navController = navController)
        }

        composable<Routes.WaitingScreenRoute> {
            val route = it.toRoute<Routes.WaitingScreenRoute>()
            WaitingScreen(userId = route.userId,navController = navController)
        }

        composable<Routes.LoginScreenRoute> {
            LoginScreen(navController = navController)
        }

        composable<Routes.HomeScreenRoute> {
            HomeScreen(navController = navController)
        }

        composable<Routes.ProfileScreenRoute> {
            ProfileScreen(navController = navController)
        }

        composable<Routes.UpdateUserScreenRoute> {
            UpdateUserScreen(navController = navController)
        }
    }
}