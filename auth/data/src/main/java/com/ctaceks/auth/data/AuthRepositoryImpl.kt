package com.ctaceks.auth.data

import com.ctaceks.auth.data.di.AuthProviderScope
import com.ctaceks.auth.data.remote.AuthService
import com.ctaceks.auth.domain.AuthInfoMutableProvider
import com.ctaceks.auth.domain.AuthRepository
import com.ctaceks.auth.domain.model.LoginRequest
import com.ctaceks.auth.domain.model.RefreshToken
import com.ctaceks.auth.domain.model.TokenPair
import com.ctaceks.settings.domain.settings.AppSettingsMutableProvider
import javax.inject.Inject

/**
 * Controls user authentication state
 */
@AuthProviderScope
class AuthRepositoryImpl @Inject constructor(
    private val authProvider: AuthInfoMutableProvider,
    private val settingsProvider: AppSettingsMutableProvider,
    private val authService: AuthService,
) : AuthRepository {

    override suspend fun signIn(loginRequest: LoginRequest): TokenPair {
        val tokenPairDto = authService.authLoginPost(loginRequest)
        val tokenPair = TokenPair(
            tokenPairDto.accessToken,
            RefreshToken(
                tokenPairDto.refreshToken.token,
                tokenPairDto.refreshToken.expiresAt
            )
        )
        authProvider.updateAuthInfo(tokenPair)
        return tokenPair
    }

    override suspend fun signUn(loginRequest: LoginRequest): TokenPair {
        val tokenPairDto = authService.authRegisterPost(loginRequest)
        val tokenPair = TokenPair(
            tokenPairDto.accessToken,
            RefreshToken(
                tokenPairDto.refreshToken.token,
                tokenPairDto.refreshToken.expiresAt
            )
        )
        authProvider.updateAuthInfo(tokenPair)
        return tokenPair
    }

    override suspend fun signOut() {
        authProvider.clearAuthInfo()
        settingsProvider.resetAll()
    }
}
