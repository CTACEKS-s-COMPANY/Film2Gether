package com.ctaceks.auth.data.remote.extensions

import com.ctaceks.auth.data.remote.model.LoginRequestDto
import com.ctaceks.auth.data.remote.model.RefreshTokenDto
import com.ctaceks.auth.domain.model.LoginRequest
import com.ctaceks.auth.domain.model.RefreshToken


internal fun LoginRequest.toDomainLayer() = LoginRequestDto(
    login = login,
    password = password,
)