package com.ctaceks.Film2Gether.di

import android.content.Context
import com.ctaceks.auth.data.di.AuthProviderScope
import com.ctaceks.auth.ui.di.AuthUiComponent
import com.ctaceks.core.di.AppScope
import com.ctaceks.edit.ui.di.EditUiComponent
import com.ctaceks.other.alarm.di.AlarmComponent
import com.ctaceks.other.work.di.SynchronizationWorkScope
import com.ctaceks.other.work.di.WorkComponent
import com.ctaceks.tasks.ui.di.TasksUiComponent
import com.ctaceks.Film2Gether.MainActivity
import com.ctaceks.Film2Gether.Film2GetherApplication
import dagger.BindsInstance
import dagger.Component

/**
 * Root of Dagger DI graph
 */
@AppScope
@AuthProviderScope
@SynchronizationWorkScope
@Component(modules = [AppModule::class])
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance context: Context,
        ): AppComponent
    }

    fun inject(app: Film2GetherApplication)
    fun inject(activity: MainActivity)

    fun authUiComponent(): AuthUiComponent.Factory
    fun tasksUiComponent(): TasksUiComponent.Factory
    fun editUiComponent(): EditUiComponent.Factory

    fun workComponent(): WorkComponent.Factory
    fun alarmComponent(): AlarmComponent.Factory
}
