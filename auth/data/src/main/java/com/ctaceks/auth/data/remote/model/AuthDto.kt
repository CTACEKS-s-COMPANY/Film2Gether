package com.ctaceks.auth.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class TokenPairDto(
    @SerialName("access_token") val accessToken: String,
    @SerialName("refresh_token") val refreshToken: RefreshTokenDto,
)

@Serializable
data class RefreshTokenDto(
    val token: String,
    @SerialName("expiration") val expiresAt: Long,
)

@Serializable
data class LoginRequestDto(
    @SerialName("username") val username: String,
    @SerialName("password") val password: String,
)