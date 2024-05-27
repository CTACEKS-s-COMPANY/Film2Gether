package com.ctaceks.auth.ui

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ctaceks.auth.ui.model.AuthAction
import com.ctaceks.auth.ui.model.AuthEvent
import com.ctaceks.auth.ui.model.AuthUiState
import com.ctaceks.core.ui.R.string.app_name
import com.ctaceks.core.ui.components.ButtonComponent
import com.ctaceks.core.ui.components.TextInputComponent
import com.ctaceks.core.ui.theme.Blue
import com.ctaceks.core.ui.theme.ExtendedTheme
import com.ctaceks.core.ui.theme.TodoAppTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

/**
 * Screen for user authentication
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthScreen(
    uiState: AuthUiState,
    uiEvent: Flow<AuthEvent>,
    onAction: (AuthAction) -> Unit,
    onSuccess: () -> Unit,

    ) {
    val context = LocalContext.current


    var isLoginError by remember { mutableStateOf(false) }
    var isPasswordError by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        uiEvent.collect {
            when (it) {
                is AuthEvent.AuthError -> {
                    Toast.makeText(context, it.error, Toast.LENGTH_LONG).show()
                    isLoginError = true
                    isPasswordError = true
                }

                AuthEvent.AuthSuccess -> onSuccess()
            }
        }
    }

    var login by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(app_name), style = ExtendedTheme.typography.title
                    )
                }, colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = ExtendedTheme.colors.backPrimary,
                    titleContentColor = ExtendedTheme.colors.labelPrimary,
                    actionIconContentColor = Blue
                )
            )
        },
        containerColor = ExtendedTheme.colors.backPrimary,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            TextInputComponent(
                modifier = Modifier,
                value = login,
                updateValue = {
                    login = it
                    isLoginError = false
                },
                isContainerError = isLoginError,
                updateIsContainerError = { isLoginError = it },
                label = "Login",
                placeholder = "Mirea💩"
            )
            TextInputComponent(
                modifier = Modifier,
                value = password,
                updateValue = {
                    password = it
                    isPasswordError = false
                },
                isContainerError = isPasswordError,
                updateIsContainerError = { isPasswordError = it },
                label = "Password",
                placeholder = "12345678"
            )
            ButtonComponent(
                modifier = Modifier,
                title = stringResource(R.string.login_button_text),
                onAction = {
                    if (login.isEmpty() || password.isEmpty()) {
                        if (login.isEmpty()) isLoginError = true
                        else isPasswordError = true
                    } else {
                        onAction(AuthAction.AuthLogin(login, password))
                    }
                })
            ButtonComponent(
                modifier = Modifier,
                title = stringResource(R.string.register_button_text),
                onAction = {
                    if (login.isEmpty() || password.isEmpty()) {
                        if (login.isEmpty()) isLoginError = true
                        else isPasswordError = true
                    } else {
                        onAction(AuthAction.AuthRegister(login, password))
                    }
                })
        }
    }
}

/*@Composable
private fun ButtonComponent(
    buttonTitle: String,
    onAction: () -> Unit,
) {
    OutlinedButton(
        onClick = onAction,
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(28),
        colors = ButtonDefaults.buttonColors(
            containerColor = ExtendedTheme.colors.backSecondary,
            contentColor = ExtendedTheme.colors.labelPrimary
        ),
        contentPadding = PaddingValues(vertical = 16.dp, horizontal = 32.dp)
    ) {
        Text(
            text = buttonTitle,
            style = ExtendedTheme.typography.titleSmall,
            color = ExtendedTheme.colors.labelPrimary
        )
    }
}*/


@Preview
@Composable
private fun AuthScreenPreview() {
    TodoAppTheme {
        AuthScreen(uiState = AuthUiState(), uiEvent = emptyFlow(), onAction = {}, onSuccess = {})
    }
}