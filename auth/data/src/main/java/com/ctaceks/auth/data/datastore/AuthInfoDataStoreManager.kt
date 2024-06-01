package com.ctaceks.auth.data.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import com.ctaceks.auth.data.di.AuthProviderScope
import com.ctaceks.auth.domain.AuthInfoMutableProvider
import com.ctaceks.auth.domain.model.AuthResponse
import com.ctaceks.auth.domain.model.RefreshToken
import com.ctaceks.auth.domain.model.TokenPair
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

/**
 * Control info about current user
 *
 * Use [AuthResponse] model
 */
@AuthProviderScope
class AuthInfoDataStoreManager @Inject constructor(
    context: Context
) : AuthInfoMutableProvider {
    private val dataStore = context.dataStore

    override fun authInfoFlow(): Flow<TokenPair> = dataStore.data.map { preferences ->
        val token = preferences[AuthKeys.ACCESS_TOKEN]
        val refreshToken = preferences[AuthKeys.REFRESH_TOKEN]
        val expiresDate = preferences[AuthKeys.EXPIRES_DATE]
        TokenPair(
            token ?: "",
            refreshToken = RefreshToken(
                refreshToken ?: "",
                expiresDate ?: 0,
            )
        )
    }

    override fun authInfo(): TokenPair = runBlocking {
        dataStore.data.first().let { preferences ->
            val token = preferences[AuthKeys.ACCESS_TOKEN]
            val refreshToken = preferences[AuthKeys.REFRESH_TOKEN]
            val expiresDate = preferences[AuthKeys.EXPIRES_DATE]
            TokenPair(
                token ?: "", refreshToken = RefreshToken(
                    refreshToken ?: "",
                    expiresDate ?: 0,
                )
            )
        }
    }

    override suspend fun updateAuthInfo(info: TokenPair) {
        dataStore.edit { preferences ->
            preferences[AuthKeys.ACCESS_TOKEN] = info.accessToken
            preferences[AuthKeys.REFRESH_TOKEN] = info.refreshToken.token
            preferences[AuthKeys.EXPIRES_DATE] = info.refreshToken.expiresAt
        }
    }

    override suspend fun updateAuthToken(token: String) {
        dataStore.edit { preferences ->
            preferences[AuthKeys.ACCESS_TOKEN] = token
        }
    }

    override suspend fun updateExpiresDate(date: Long) {
        dataStore.edit { preferences ->
            preferences[AuthKeys.EXPIRES_DATE] = date
        }
    }

    override suspend fun clearAuthInfo() {
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }
}
