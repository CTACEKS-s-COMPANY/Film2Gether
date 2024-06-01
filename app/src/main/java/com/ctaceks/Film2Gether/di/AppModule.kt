package com.ctaceks.Film2Gether.di

import com.ctaceks.auth.data.di.AuthDataModule
import com.ctaceks.auth.ui.di.AuthUiModule
import com.ctaceks.core.data.di.NetworkModule
import com.ctaceks.edit.ui.roomBeforeStart.di.RoomBeforeStartUiModule
import com.ctaceks.edit.ui.roomBeforeStart.di.RoomStartedUiModule
import com.ctaceks.settings.data.di.SettingsDataModule
import com.ctaceks.tasks.data.di.TasksDataModule
import com.ctaceks.tasks.ui.di.TasksUiModule
import dagger.Module

/**
 * Contains all dagger modules
 */
@Module(includes = [
    ActivityModule::class,
    NetworkModule::class,
    AuthDataModule::class, AuthUiModule::class,
    TasksDataModule::class, TasksUiModule::class,
    RoomBeforeStartUiModule::class,
    RoomStartedUiModule::class,
    SettingsDataModule::class
])
interface AppModule