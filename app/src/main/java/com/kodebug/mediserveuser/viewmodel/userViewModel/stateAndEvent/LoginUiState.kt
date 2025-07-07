package com.kodebug.mediserveuser.viewmodel.userViewModel.stateAndEvent

import com.kodebug.mediserveuser.presentation.navigation.Routes

data class LoginUiState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val error: String? = null
)

sealed class LoginUiEvent {
    data class Navigate(val route: Routes) : LoginUiEvent()
    data class ShowToast(val message: String) : LoginUiEvent()
}