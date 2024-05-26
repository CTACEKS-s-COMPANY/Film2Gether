package com.ctaceks.auth.data

import com.ctaceks.auth.data.di.AuthProviderScope
import com.ctaceks.auth.data.remote.AuthService
import com.ctaceks.auth.data.remote.extensions.toDataLayer
import com.ctaceks.auth.data.remote.extensions.toDomainLayer
import com.ctaceks.auth.domain.AuthInfoMutableProvider
import com.ctaceks.auth.domain.AuthRepository
import com.ctaceks.auth.domain.model.LoginRequest
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
        val tokenPair = authService.authLoginPost(loginRequest.toDomainLayer()).toDataLayer()
        authProvider.updateAuthInfo(tokenPair)
        return tokenPair
    }

    override suspend fun signUp(loginRequest: LoginRequest): TokenPair {
        val tokenPair = authService.authRegisterPost(loginRequest.toDomainLayer()).toDataLayer()
        authProvider.updateAuthInfo(tokenPair)
        return tokenPair
    }

    override suspend fun signOut() {
        authProvider.clearAuthInfo()
        settingsProvider.resetAll()
    }
}
