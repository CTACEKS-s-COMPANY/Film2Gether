package com.ctaceks.edit.ui.di

import com.ctaceks.core.di.FeatureScope
import com.ctaceks.edit.ui.TaskEditViewModel
import dagger.Subcomponent

/**
 * Dagger component for edit ui layer
 */
@Subcomponent
@FeatureScope
interface EditUiComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): EditUiComponent
    }

    fun getViewModel(): TaskEditViewModel
}

interface EditUiComponentProvider {
    fun provideEditUiComponent(): EditUiComponent
}
