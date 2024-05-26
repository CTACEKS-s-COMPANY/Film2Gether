package com.ctaceks.auth.data.remote

import com.ctaceks.auth.data.remote.model.TokenPairDto
import com.ctaceks.auth.domain.model.LoginRequest
import com.ctaceks.auth.domain.model.TokenPair


interface AuthService {

    suspend fun authLoginPost(user: LoginRequest): TokenPairDto

    suspend fun authRegisterPost(user: LoginRequest): TokenPairDto
}
