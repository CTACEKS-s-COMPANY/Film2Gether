package com.ctaceks.tasks.ui.model

import com.ctaceks.tasks.ui.TasksScreen

/**
 * Provides info for auth ui events on [TasksScreen]
 */
sealed class TasksEvent {
    object ShowSettings: TasksEvent()
    object CreateRoom: TasksEvent()
    object JoinTheRoom: TasksEvent()
    object SignOut: TasksEvent()
}
