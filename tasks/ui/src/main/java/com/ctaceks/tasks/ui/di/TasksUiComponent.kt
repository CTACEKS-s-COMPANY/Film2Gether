package com.ctaceks.tasks.ui.di

import com.ctaceks.core.di.FeatureScope
import com.ctaceks.tasks.ui.TasksViewModel
import dagger.Subcomponent

/**
 * Dagger component for tasks ui layer
 */
@Subcomponent
@FeatureScope
interface TasksUiComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): TasksUiComponent
    }

    fun getViewModel(): TasksViewModel
}

interface TasksUiComponentProvider {
    fun provideTasksUiComponent(): TasksUiComponent
}
