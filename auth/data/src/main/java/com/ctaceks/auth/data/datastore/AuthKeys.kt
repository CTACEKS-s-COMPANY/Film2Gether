package com.ctaceks.auth.data.datastore

import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

/**
 * DataStore authentication keys
 */
internal object AuthKeys {
    val ACCESS_TOKEN = stringPreferencesKey("auth_token")
    val REFRESH_TOKEN = stringPreferencesKey("refresh_token")
    val EXPIRES_DATE = longPreferencesKey("expires_date")
}
