package com.ctaceks.edit.ui.roomBeforeStart.di

import com.ctaceks.core.di.FeatureScope
import com.ctaceks.edit.ui.roomBeforeStart.RoomBeforeStartViewModel
import dagger.Subcomponent

/**
 * Dagger component for edit ui layer
 */
@Subcomponent
@FeatureScope
interface RoomBeforeStartUiComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): RoomBeforeStartUiComponent
    }

    fun getViewModel(): RoomBeforeStartViewModel
}

interface RoomBeforeStartUiComponentProvider {
    fun provideEditUiComponent(): RoomBeforeStartUiComponent
}
