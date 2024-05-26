package com.ctaceks.auth.ui.di

import com.ctaceks.auth.ui.AuthViewModel
import com.ctaceks.core.di.FeatureScope
import dagger.Subcomponent

/**
 * Dagger component for auth ui layer
 */
@Subcomponent
@FeatureScope
interface AuthUiComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): AuthUiComponent
    }

    fun getViewModel(): AuthViewModel
}

interface AuthUiComponentProvider {
    fun provideAuthUiComponent(): AuthUiComponent
}
