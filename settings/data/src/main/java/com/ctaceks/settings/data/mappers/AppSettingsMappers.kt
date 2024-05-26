package com.ctaceks.settings.data.mappers

import com.ctaceks.settings.data.model.AppSettingsDto
import com.ctaceks.settings.domain.model.AppSettings

fun AppSettings.toDto() = AppSettingsDto(
    theme = theme
)

fun AppSettingsDto.toModel() = AppSettings(
    theme = theme
)