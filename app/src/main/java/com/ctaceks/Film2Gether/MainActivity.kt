package com.ctaceks.Film2Gether

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.ctaceks.auth.domain.AuthInfoProvider
import com.ctaceks.core.ui.theme.TodoAppTheme
import com.ctaceks.other.work.SynchronizationWork
import com.ctaceks.settings.domain.settings.AppSettingsProvider
import com.ctaceks.Film2Gether.utils.PermissionsChecker
import com.ctaceks.Film2Gether.utils.isDarkTheme
import javax.inject.Inject

/**
 * Entry point for app UI
 *
 * Starts synchronization work with [SynchronizationWork]
 */
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var authProvider: AuthInfoProvider
    @Inject
    lateinit var syncWork: SynchronizationWork
    @Inject
    lateinit var settings: AppSettingsProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (applicationContext as Film2GetherApplication).appComponent.inject(this)
        syncWork.enqueuePeriodicSynchronizationWork()
        setContent {
            val settings by settings.settingsFlow().collectAsState(settings.settings())
            PermissionsChecker()
            TodoAppTheme(
                darkTheme = settings.theme.isDarkTheme()
            ) {
                FilmNavigation(authProvider)
            }
        }
    }

    override fun onStop() {
        super.onStop()
        syncWork.beginOneTimeSynchronizationWork()
    }
}