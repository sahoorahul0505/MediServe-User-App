package com.kodebug.mediserveuser.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kodebug.mediserveuser.data.datastore.DataStoreManager
import com.kodebug.mediserveuser.data.repository.userRepo.UserRepository
import com.kodebug.mediserveuser.presentation.navigation.Routes
import com.kodebug.mediserveuser.viewmodel.userViewModel.stateAndEvent.CreateUiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val dataStore: DataStoreManager,
    private val userRepository: UserRepository
) : ViewModel(){
    private val _splashEvent = MutableSharedFlow<CreateUiEvent>()
    val splashEvent = _splashEvent.asSharedFlow()

    fun checkUserSession(){
        viewModelScope.launch {
            val userId = dataStore.userIdFlow.firstOrNull()
            if (userId.isNullOrEmpty()){
                _splashEvent.emit(CreateUiEvent.Navigate(Routes.LoginScreenRoute))
                return@launch
            }

            val result = userRepository.isUserApproved(userId)
            result.fold(
                onSuccess = { isApproved ->
                    if (isApproved){
                        _splashEvent.emit(CreateUiEvent.Navigate(Routes.HomeScreenRoute))
                        _splashEvent.emit(CreateUiEvent.ShowToast("Welcome back"))
                    }else{
                        _splashEvent.emit(CreateUiEvent.Navigate(Routes.WaitingScreenRoute(userId)))
                        _splashEvent.emit(CreateUiEvent.ShowToast("Please wait for approval"))
                    }
                },
                onFailure = {
                    _splashEvent.emit(CreateUiEvent.ShowToast("Failed to verify user"))
                    _splashEvent.emit(CreateUiEvent.Navigate(Routes.LoginScreenRoute))
                }
            )
        }
    }
}