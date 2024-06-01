package com.ctaceks.auth.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ctaceks.auth.domain.AuthRepository
import com.ctaceks.auth.domain.model.LoginRequest
import com.ctaceks.auth.ui.model.AuthAction
import com.ctaceks.auth.ui.model.AuthEvent
import com.ctaceks.auth.ui.model.AuthUiState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * An intermediate layer between auth ui and auth logic
 *
 * Uses [AuthRepository] to authenticate user
 */
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {
    var uiState by mutableStateOf(AuthUiState())
        private set

    private val _uiEvent = Channel<AuthEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onAction(action: AuthAction) {
        when (action) {
            is AuthAction.AuthLogin -> launchAuthentication(action.login, action.password)
            is AuthAction.AuthRegister -> launchRegistration(action.login, action.password)
        }
    }

    private fun launchAuthentication(login: String, password: String) {
        viewModelScope.launch {
            val loginRequest = LoginRequest(login, password)
            val request = repository.signIn(loginRequest)
            if (request != null) {
                _uiEvent.send(AuthEvent.AuthSuccess)
            } else {
                //Todo make normal error
                _uiEvent.send(AuthEvent.AuthError("Something goes wrong"))
            }
        }
    }

    private fun launchRegistration(login: String, password: String) {
        viewModelScope.launch {
            val loginRequest = LoginRequest(login, password)
            val request = repository.signUp(loginRequest)
            if (request != null) {
                _uiEvent.send(AuthEvent.AuthSuccess)
            } else {
                //Todo make normal error
                _uiEvent.send(AuthEvent.AuthError("Something goes wrong"))
            }
        }
    }
}
