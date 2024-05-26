package com.ctaceks.auth.ui.model

/**
 * Provides info for auth ui events
 */
sealed class AuthEvent {
    object AuthError: AuthEvent()
    object AuthSuccess: AuthEvent()
}
