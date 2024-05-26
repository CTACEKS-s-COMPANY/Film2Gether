package com.ctaceks.settings.data

import android.content.Context
import com.ctaceks.core.di.AppScope
import com.ctaceks.settings.data.datastore.dataStore
import com.ctaceks.settings.data.mappers.toModel
import com.ctaceks.settings.data.model.AppSettingsDto
import com.ctaceks.settings.domain.model.AppSettings
import com.ctaceks.settings.domain.model.Theme
import com.ctaceks.settings.domain.settings.AppSettingsMutableProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@AppScope
class AppSettingsDataStoreManager @Inject constructor(
    context: Context
): AppSettingsMutableProvider {
    private val dataStore = context.dataStore

    override fun settingsFlow(): Flow<AppSettings> =
        dataStore.data.map(AppSettingsDto::toModel)

    override fun settings(): AppSettings = runBlocking {
        dataStore.data.first().toModel()
    }


    override suspend fun updateTheme(theme: Theme) {
        dataStore.updateData {
            it.copy(
                theme = theme
            )
        }
    }

    override suspend fun resetAll() {
        dataStore.updateData { AppSettingsDto() }
    }
}