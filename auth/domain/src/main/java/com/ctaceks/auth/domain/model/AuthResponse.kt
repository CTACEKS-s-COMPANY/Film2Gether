package com.ctaceks.auth.domain.model

data class AuthResponse(
    val token: String = "",
    val refreshToken: String = "",
    val expiresDate: Long = 0,
)