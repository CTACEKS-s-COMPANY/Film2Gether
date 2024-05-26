package com.ctaceks.Film2Gether.di

import android.content.Context
import android.content.Intent
import com.ctaceks.core.di.AppScope
import com.ctaceks.other.alarm.di.MainActivityIntent
import com.ctaceks.Film2Gether.MainActivity
import dagger.Module
import dagger.Provides

@Module
class ActivityModule {
    @Provides
    @AppScope
    @MainActivityIntent
    fun provideMainActivityIntent(
        context: Context
    ) = Intent(context, MainActivity::class.java)
}
