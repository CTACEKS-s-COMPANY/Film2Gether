package com.ctaceks.edit.ui.roomBeforeStart.di

import com.ctaceks.core.di.FeatureScope
import com.ctaceks.edit.ui.roomBeforeStart.RoomBeforeStartViewModel
import com.ctaceks.edit.ui.roomStarted.RoomStartedViewModel
import dagger.Subcomponent

/**
 * Dagger component for edit ui layer
 */
@Subcomponent
@FeatureScope
interface RoomStartedUiComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): RoomStartedUiComponent
    }

    fun getViewModel(): RoomStartedViewModel
}

interface RoomStartedUiComponentProvider {
    fun provideRoomStartedUiComponent(): RoomStartedUiComponent
}
