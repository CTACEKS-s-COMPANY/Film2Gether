package com.ctaceks.auth.ui.model

/**
 * Contains info about auth ui actions
 */
sealed class AuthAction {
    data class AuthRegister(val login: String, val password: String) : AuthAction()
    data class AuthLogin(val login: String, val password: String) : AuthAction()
}
