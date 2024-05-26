package com.ctaceks.auth.data.remote.extensions

import com.ctaceks.auth.data.remote.model.RefreshTokenDto
import com.ctaceks.auth.data.remote.model.TokenPairDto
import com.ctaceks.auth.domain.model.RefreshToken
import com.ctaceks.auth.domain.model.TokenPair

internal fun TokenPairDto.toDataLayer() = TokenPair(
    accessToken = accessToken,
    refreshToken = refreshToken.toDataLayer(),
)

internal fun RefreshTokenDto.toDataLayer() = RefreshToken(
    token = token,
    expiresAt = expiresAt,
)