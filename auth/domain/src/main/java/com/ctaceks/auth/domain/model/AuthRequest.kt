package com.ctaceks.auth.domain.model

data class AuthRequest(
    val login: String = "",
    val password: String = "",
)
