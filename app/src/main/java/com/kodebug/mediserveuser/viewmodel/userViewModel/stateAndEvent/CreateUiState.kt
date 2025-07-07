package com.kodebug.mediserveuser.viewmodel.userViewModel.stateAndEvent

import com.kodebug.mediserveuser.presentation.navigation.Routes

data class CreateUiState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val error: String? = null
)

sealed class CreateUiEvent {
    data class Navigate(val route: Routes) : CreateUiEvent()
    data class ShowToast(val message: String) : CreateUiEvent()
}