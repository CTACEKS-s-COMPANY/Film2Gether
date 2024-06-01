package com.ctaceks.auth.domain

import com.ctaceks.auth.domain.model.AuthInfo
import com.ctaceks.auth.domain.model.AuthResponse
import com.ctaceks.auth.domain.model.TokenPair
import kotlinx.coroutines.flow.Flow

/**
 * Immutable abstraction for providing authentication info
 */
interface AuthInfoProvider {
    fun authInfoFlow(): Flow<TokenPair>
    fun authInfo(): TokenPair

}

/**
 * Mutable abstraction for controlling authentication info
 */
interface AuthInfoMutableProvider: AuthInfoProvider {
    suspend fun updateAuthInfo(info: TokenPair)
    suspend fun updateAuthToken(token: String)
    suspend fun updateExpiresDate(date: Long)
    suspend fun clearAuthInfo()
}
