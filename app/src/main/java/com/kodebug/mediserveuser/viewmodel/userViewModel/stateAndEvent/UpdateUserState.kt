package com.kodebug.mediserveuser.viewmodel.userViewModel.stateAndEvent

import com.kodebug.mediserveuser.presentation.navigation.Routes

data class UpdateUserState(
    val isLoading: Boolean = false,
    val name: String = "",
    val email: String = "",
    val phoneNumber: String = "",
    val address: String = "",
    val pinCode: String = "",
    val password: String = "",
    val successMessage: String? = null,
    val error: String? = null
)

sealed class UpdateUserUiEvent {
    data class ShowToast(val message: String) : UpdateUserUiEvent()
    data class Navigate(val route: Routes) : UpdateUserUiEvent()
}
