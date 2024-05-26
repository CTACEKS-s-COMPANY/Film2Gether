package com.ctaceks.auth.domain

import com.ctaceks.auth.domain.model.LoginRequest
import com.ctaceks.auth.domain.model.TokenPair

/**
 * Authentication repository abstraction
 */
interface AuthRepository {
    suspend fun signIn(loginRequest: LoginRequest): TokenPair
    suspend fun signUn(loginRequest: LoginRequest): TokenPair
    suspend fun signOut()
}
