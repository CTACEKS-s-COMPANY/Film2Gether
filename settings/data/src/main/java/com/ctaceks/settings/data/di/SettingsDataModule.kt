package com.ctaceks.settings.data.di

import com.ctaceks.core.di.AppScope
import com.ctaceks.settings.data.AppSettingsDataStoreManager
import com.ctaceks.settings.domain.settings.AppSettingsMutableProvider
import com.ctaceks.settings.domain.settings.AppSettingsProvider
import dagger.Binds
import dagger.Module

@Module
interface SettingsDataModule {

    @Binds
    @AppScope
    fun provideSettingsProvider(
        settingsManager: AppSettingsDataStoreManager
    ): AppSettingsProvider

    @Binds
    @AppScope
    fun provideMutableSettingsProvider(
        settingsManager: AppSettingsDataStoreManager
    ): AppSettingsMutableProvider
}