package com.ctaceks.settings.data.model

import com.ctaceks.settings.domain.model.Theme
import kotlinx.serialization.Serializable

@Serializable
data class AppSettingsDto(
    val theme: Theme = Theme.SYSTEM
)