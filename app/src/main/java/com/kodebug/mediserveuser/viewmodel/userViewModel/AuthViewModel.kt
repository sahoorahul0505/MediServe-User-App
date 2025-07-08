package com.kodebug.mediserveuser.viewmodel.userViewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kodebug.mediserveuser.data.datastore.DataStoreManager
import com.kodebug.mediserveuser.data.repository.userRepo.UserRepository
import com.kodebug.mediserveuser.presentation.navigation.Routes
import com.kodebug.mediserveuser.viewmodel.userViewModel.stateAndEvent.CreateUiEvent
import com.kodebug.mediserveuser.viewmodel.userViewModel.stateAndEvent.CreateUiState
import com.kodebug.mediserveuser.viewmodel.userViewModel.stateAndEvent.LoginUiEvent
import com.kodebug.mediserveuser.viewmodel.userViewModel.stateAndEvent.LoginUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AuthViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val dataStore: DataStoreManager
) : ViewModel() {

    // --- CREATE USER STATE ---
    private val _createUiState = MutableStateFlow(CreateUiState())
    val createUiState: StateFlow<CreateUiState> = _createUiState

    // --- CREATE USER EVENT ---
    private val _createUiEvent = MutableSharedFlow<CreateUiEvent>()
    val createUiEvent = _createUiEvent.asSharedFlow()

    // --- LOGIN USER STATE ---
    private val _loginUiState = MutableStateFlow(LoginUiState())
    val loginUiState: StateFlow<LoginUiState> = _loginUiState

    // --- LOGIN USER EVENT ---
    private val _loginUiEvent = MutableSharedFlow<LoginUiEvent>()
    val loginUiEvent = _loginUiEvent.asSharedFlow()

    //--- GET USER STATE ---
//    private val _getUserState = MutableStateFlow(UserState())
//    val getUserState: StateFlow<UserState> = _getUserState


    // --- CREATE USER FUNCTION ---
    fun createUser(
        name: String,
        phoneNumber: String,
        email: String,
        password: String,
        address: String,
        pinCode: String
    ) {
        viewModelScope.launch {
            _createUiState.value = createUiState.value.copy(isLoading = true)

            val createResult = userRepository.createUser(name, phoneNumber, email, password, address, pinCode)
            createResult.fold(
                onSuccess = { userId ->
                    Log.d("userId", "createUser: $userId")
                    dataStore.saveUserId(userId)
//                    checkApproval(userId)
                    _createUiEvent.emit(CreateUiEvent.ShowToast("Registration Successful"))
//                    _userUiState.value = UserUiState(isSuccess = true)
                    _createUiEvent.emit(CreateUiEvent.Navigate(Routes.WaitingScreenRoute(userId)))
                },
                onFailure = { exception ->
                    _createUiState.value =
                        CreateUiState(error = exception.message) // this error will come from the Repository
                    _createUiEvent.emit(CreateUiEvent.ShowToast("Registration failed"))
                }
            )
        }
    }


    // --- CHECK APPROVAL PRIVET FUN FOR LOGIN USER FUNCTION ---
    private suspend fun checkApproval(userId: String) {
        val approvalResult = userRepository.isUserApproved(userId)
        approvalResult.fold(
            onSuccess = { isApproved ->
                _loginUiState.value = LoginUiState(isSuccess = true)
                _loginUiEvent.emit(
                    if (isApproved) LoginUiEvent.Navigate(Routes.HomeScreenRoute)
                    else LoginUiEvent.Navigate(Routes.WaitingScreenRoute(userId))
                )
            },
            onFailure = { exception ->
                _createUiState.value = CreateUiState(error = exception.message)
                _createUiEvent.emit(CreateUiEvent.ShowToast("Failed to verify user"))
            }
        )
    }


    // --- LOGIN USER FUNCTION ---
    fun loginUser(email: String, password: String) {
        viewModelScope.launch {
            _loginUiState.value = loginUiState.value.copy(isLoading = true)

            val loginResult = userRepository.loginUser(email, password)
            loginResult.fold(
                onSuccess = { userId ->
                    dataStore.saveUserId(userId)
                    checkApproval(userId)
                },
                onFailure = { exception ->
                    _loginUiState.value = LoginUiState(error = exception.message)
//                    _loginUiEvent.emit(LoginUiEvent.ShowToast(message = "login failed"))
                }
            )
        }
    }

    // --- PROFILE FUNCTION ---
//    fun getSpecificUser(userId: String) {
//        viewModelScope.launch {
//            _getUserState.value = getUserState.value.copy(isLoading = true)
//            val result = userRepository.getSpecificUser(userId)
//            result.fold(
//                onSuccess = { userData ->
//                    _getUserState.value = UserState(user = userData)
//                },
//                onFailure = { exception ->
//                    _getUserState.value = getUserState.value.copy(error = exception.message)
//                }
//            )
//        }
//    }


    // --- LOGOUT ---
    fun logOut() {
        viewModelScope.launch {
            dataStore.clearUserId()
            _loginUiEvent.emit(LoginUiEvent.ShowToast(message = "Logged out successfully"))
            _loginUiEvent.emit(LoginUiEvent.Navigate(Routes.LoginScreenRoute))
        }
    }
}