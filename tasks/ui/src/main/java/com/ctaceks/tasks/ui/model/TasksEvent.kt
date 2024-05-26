package com.ctaceks.tasks.ui.model

import com.ctaceks.tasks.ui.TasksScreen

/**
 * Provides info for auth ui events on [TasksScreen]
 */
sealed class TasksEvent {
    object ConnectionError: TasksEvent()
    object UndoNotification: TasksEvent()
    object ShowSettings: TasksEvent()
    data class NavigateToEditTask(val id: String): TasksEvent()
    object NavigateToNewTask: TasksEvent()
    object CreateRoom: TasksEvent()
    object JoinTheRoom: TasksEvent()
    object SignOut: TasksEvent()
}
