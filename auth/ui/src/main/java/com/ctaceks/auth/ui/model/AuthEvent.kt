package com.ctaceks.auth.ui.model

/**
 * Provides info for auth ui events
 */
sealed class AuthEvent {
    data class AuthError(val error: String): AuthEvent()
    object AuthSuccess: AuthEvent()
}
