package com.kodebug.mediserveuser.presentation.navigation

import kotlinx.serialization.Serializable

sealed class Routes {

    @Serializable
    object SplashScreenRoute : Routes()

    @Serializable
    object RegisterScreenRoute : Routes()

    @Serializable
    data class WaitingScreenRoute(val userId: String) : Routes()

    @Serializable
    object LoginScreenRoute : Routes()


    @Serializable
    object HomeScreenRoute : Routes()


    @Serializable
    object ProfileScreenRoute : Routes()

    @Serializable
    object UpdateUserScreenRoute : Routes()
}