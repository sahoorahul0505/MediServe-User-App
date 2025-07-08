package com.kodebug.mediserveuser.viewmodel.userViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kodebug.mediserveuser.data.datastore.DataStoreManager
import com.kodebug.mediserveuser.data.repository.userRepo.UserRepository
import com.kodebug.mediserveuser.presentation.navigation.Routes
import com.kodebug.mediserveuser.viewmodel.userViewModel.stateAndEvent.UpdateUserState
import com.kodebug.mediserveuser.viewmodel.userViewModel.stateAndEvent.UpdateUserUiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpdateUserViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val dataStore: DataStoreManager
) : ViewModel(){
    private val _updateUiState = MutableStateFlow(UpdateUserState())
    val updateUiState: StateFlow<UpdateUserState> = _updateUiState

    private val _updateUiEvent = MutableSharedFlow<UpdateUserUiEvent>()
    val updateUiEvent: SharedFlow<UpdateUserUiEvent> = _updateUiEvent

    init {
        viewModelScope.launch {
            val userId = dataStore.userIdFlow.firstOrNull()
            if (userId != null){
                val result = userRepository.getSpecificUser(userId)
                result.fold(
                    onSuccess = {
                        _updateUiState.value = _updateUiState.value.copy(
                            name = it.name,
                            email = it.email,
                            phoneNumber = it.phoneNumber,
                            address = it.address,
                            pinCode = it.pinCode,
                            password = it.password
                        )
                    },
                    onFailure = {}
                )
            }
        }
    }


    fun onFieldChange(field: String, value: String) {
        _updateUiState.value = when (field) {
            "name" -> _updateUiState.value.copy(name = value)
            "email" -> _updateUiState.value.copy(email = value)
            "phoneNumber" -> _updateUiState.value.copy(phoneNumber = value)
            "address" -> _updateUiState.value.copy(address = value)
            "pinCode" -> _updateUiState.value.copy(pinCode = value)
            "password" -> _updateUiState.value.copy(password = value)
            else -> _updateUiState.value
        }
    }


    fun updateUser() {
        viewModelScope.launch {
            _updateUiState.value = _updateUiState.value.copy(isLoading = true)

            val userId = dataStore.userIdFlow.firstOrNull()
            if (userId.isNullOrEmpty()) {
                _updateUiState.value = _updateUiState.value.copy(isLoading = false)
                _updateUiEvent.emit(UpdateUserUiEvent.ShowToast("User ID missing"))
                return@launch
            }

            val result = userRepository.updateUser(
                userId,
                updateUiState.value.name,
                updateUiState.value.email,
                updateUiState.value.phoneNumber,
                updateUiState.value.address,
                updateUiState.value.pinCode,
                updateUiState.value.password
            )

            result.fold(
                onSuccess = {
                    _updateUiState.value = _updateUiState.value.copy(isLoading = false)
                    _updateUiEvent.emit(UpdateUserUiEvent.ShowToast("Profile updated"))
                    _updateUiEvent.emit(UpdateUserUiEvent.Navigate(Routes.ProfileScreenRoute))
                },
                onFailure = {
                    _updateUiState.value = _updateUiState.value.copy(isLoading = false)
                    _updateUiEvent.emit(UpdateUserUiEvent.ShowToast("Update failed"))
                }
            )
        }
    }
}