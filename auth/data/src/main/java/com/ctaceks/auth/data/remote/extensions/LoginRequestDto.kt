package com.ctaceks.auth.data.remote.extensions

import com.ctaceks.auth.data.remote.model.LoginRequestDto
import com.ctaceks.auth.domain.model.LoginRequest


internal fun LoginRequest.toDomainLayer() = LoginRequestDto(
    username = username,
    password = password,
)