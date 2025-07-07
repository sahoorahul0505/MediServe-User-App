package com.kodebug.mediserveuser.viewmodel.userViewModel.stateAndEvent

sealed class LogOutEvent {
    data class ShowToast(val message: String) : LogOutEvent()
    data class Navigate(val route: String) : LogOutEvent()
}