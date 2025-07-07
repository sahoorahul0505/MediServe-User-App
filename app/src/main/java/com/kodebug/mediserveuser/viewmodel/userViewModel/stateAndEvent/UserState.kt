package com.kodebug.mediserveuser.viewmodel.userViewModel.stateAndEvent

import com.kodebug.mediserveuser.data.model.userModel.SpecificUserData
import com.kodebug.mediserveuser.presentation.navigation.Routes

data class UserState(
    val isLoading : Boolean = false,
    val user : SpecificUserData? = null,
    val error : String? = null
)

sealed class UserUiEvent {
    data class ShowToast(val message : String) : UserUiEvent()
    data class Navigate(val route : Routes) : UserUiEvent()
}
