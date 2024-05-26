package com.ctaceks.auth.ui.di

import com.ctaceks.auth.data.di.AuthProviderScope
import com.ctaceks.auth.data.remote.AuthServiceImpl
import com.ctaceks.auth.data.remote.AuthService
import dagger.Binds
import dagger.Module

/**
 * Dagger authentication ui module
 */
@Module(subcomponents = [AuthUiComponent::class])
interface AuthUiModule {

    @Binds
    @AuthProviderScope
    fun bindAuthService(authServiceImpl: AuthServiceImpl): AuthService
}
