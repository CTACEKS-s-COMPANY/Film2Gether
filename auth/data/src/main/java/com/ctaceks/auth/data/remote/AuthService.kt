package com.ctaceks.auth.data.remote

import com.ctaceks.auth.data.remote.model.LoginRequestDto
import com.ctaceks.auth.data.remote.model.TokenPairDto
import com.ctaceks.auth.domain.model.LoginRequest
import com.ctaceks.auth.domain.model.TokenPair


interface AuthService {

    suspend fun authLoginPost(user: LoginRequestDto): TokenPairDto

    suspend fun authRegisterPost(user: LoginRequestDto): TokenPairDto
}
