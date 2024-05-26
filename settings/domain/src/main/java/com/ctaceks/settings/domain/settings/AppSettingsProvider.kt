package com.ctaceks.settings.domain.settings

import com.ctaceks.settings.domain.model.AppSettings
import com.ctaceks.settings.domain.model.Theme
import kotlinx.coroutines.flow.Flow

interface AppSettingsProvider {
    fun settingsFlow(): Flow<AppSettings>
    fun settings(): AppSettings
}

interface AppSettingsMutableProvider: AppSettingsProvider {
    suspend fun updateTheme(theme: Theme)
    suspend fun resetAll()
}