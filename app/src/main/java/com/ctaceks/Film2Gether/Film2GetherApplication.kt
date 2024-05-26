package com.ctaceks.Film2Gether

import android.app.Application
import android.app.NotificationManager
import androidx.work.Configuration
import com.ctaceks.Film2Gether.di.AppComponent
import com.ctaceks.Film2Gether.di.DaggerAppComponent
import com.ctaceks.auth.ui.di.AuthUiComponent
import com.ctaceks.auth.ui.di.AuthUiComponentProvider
import com.ctaceks.edit.ui.di.EditUiComponent
import com.ctaceks.edit.ui.di.EditUiComponentProvider
import com.ctaceks.other.alarm.AlarmNotificationChannel
import com.ctaceks.other.alarm.di.AlarmComponent
import com.ctaceks.other.alarm.di.AlarmComponentProvider
import com.ctaceks.other.work.SynchronizationNotificationChannel
import com.ctaceks.other.work.di.WorkComponent
import com.ctaceks.other.work.di.WorkComponentProvider
import com.ctaceks.other.work.factory.SynchronizationWorkerFactory
import com.ctaceks.tasks.ui.di.TasksUiComponent
import com.ctaceks.tasks.ui.di.TasksUiComponentProvider
import javax.inject.Inject

/**
 * Custom Application class allows to hold reference to [appComponent]
 * as long as application lives.
 *
 * Provides app features dagger components
 *
 * Creates [SynchronizationNotificationChannel]
 */
class Film2GetherApplication : Application(), Configuration.Provider,
    AuthUiComponentProvider,
    TasksUiComponentProvider,
    EditUiComponentProvider,
    WorkComponentProvider,
    AlarmComponentProvider {
    lateinit var appComponent: AppComponent

    @Inject
    lateinit var syncWorkFactory: SynchronizationWorkerFactory

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create(this)
        appComponent.inject(this)
        val syncChannel = SynchronizationNotificationChannel()
        val alarmChannel = AlarmNotificationChannel()

        val notificationManager = getSystemService(NotificationManager::class.java)
        notificationManager.createNotificationChannel(syncChannel.channel)
        notificationManager.createNotificationChannel(alarmChannel.channel)
    }

    override fun getWorkManagerConfiguration() =
        Configuration.Builder()
            .setWorkerFactory(syncWorkFactory)
            .build()

    override fun provideAuthUiComponent(): AuthUiComponent =
        appComponent.authUiComponent().create()

    override fun provideTasksUiComponent(): TasksUiComponent =
        appComponent.tasksUiComponent().create()

    override fun provideEditUiComponent(): EditUiComponent =
        appComponent.editUiComponent().create()

    override fun provideWorkComponent(): WorkComponent =
        appComponent.workComponent().create()

    override fun provideAlarmComponent(): AlarmComponent =
        appComponent.alarmComponent().create()
}
