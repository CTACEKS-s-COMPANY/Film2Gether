package com.ctaceks.auth.domain.model


data class TokenPair(
    val accessToken: String,
    val refreshToken: RefreshToken,
)

data class RefreshToken(
    val token: String,
    val expiresAt: Long,
)


data class LoginRequest(
    val username: String,
    val password: String,
)