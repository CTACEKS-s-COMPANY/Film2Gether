package com.ctaceks.auth.data.di

import com.ctaceks.auth.data.AuthRepositoryImpl
import com.ctaceks.auth.data.datastore.AuthInfoDataStoreManager
import com.ctaceks.auth.domain.AuthInfoMutableProvider
import com.ctaceks.auth.domain.AuthInfoProvider
import com.ctaceks.auth.domain.AuthRepository
import dagger.Binds
import dagger.Module

/**
 * Dagger module of auth data layer
 */
@Module
interface AuthDataModule {
    @Binds
    @AuthProviderScope
    fun provideAuthRepository(
        repo: AuthRepositoryImpl
    ): AuthRepository

    @Binds
    @AuthProviderScope
    fun provideAuthInfoMutableProvider(
        manager: AuthInfoDataStoreManager
    ): AuthInfoMutableProvider

    @Binds
    @AuthProviderScope
    fun provideAuthInfoProvider(
        manager: AuthInfoDataStoreManager
    ): AuthInfoProvider
}