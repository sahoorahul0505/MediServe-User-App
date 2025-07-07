package com.kodebug.mediserveuser.viewmodel.userViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kodebug.mediserveuser.data.datastore.DataStoreManager
import com.kodebug.mediserveuser.data.repository.userRepo.UserRepository
import com.kodebug.mediserveuser.presentation.navigation.Routes
import com.kodebug.mediserveuser.viewmodel.userViewModel.stateAndEvent.UserState
import com.kodebug.mediserveuser.viewmodel.userViewModel.stateAndEvent.UserUiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val dataStore: DataStoreManager,
    private val userRepository: UserRepository
) : ViewModel() {

    private val _userState = MutableStateFlow(UserState())
    val userState: StateFlow<UserState> = _userState

    private val _userUiEvent = MutableSharedFlow<UserUiEvent>()
    val userUiEvent : SharedFlow<UserUiEvent> = _userUiEvent

    init {
        viewModelScope.launch {
            dataStore.userIdFlow.firstOrNull()?.let { userid ->
                fetchUserProfile(userid)
            } ?: run {
                // Handle the case where userId is null
                _userState.value = UserState(error = "User ID not found")
            }
        }
    }

    private fun fetchUserProfile(userId: String){
        viewModelScope.launch {
            _userState.value = userState.value.copy(isLoading = true)
            val userResult = userRepository.getSpecificUser(userId)
            userResult.fold(
                onSuccess = { userData ->
                    _userState.value = UserState(user = userData)
                },
                onFailure = { exception ->
                    _userState.value = UserState(error = exception.message)
                }
            )
        }
    }

    fun logOut(){
        viewModelScope.launch {
            dataStore.clearUserData()
            _userUiEvent.emit(UserUiEvent.ShowToast(message = "Logged out successfully"))
            _userUiEvent.emit(UserUiEvent.Navigate(Routes.LoginScreenRoute))
        }
    }


}