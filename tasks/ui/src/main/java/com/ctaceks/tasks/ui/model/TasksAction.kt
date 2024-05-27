package com.ctaceks.tasks.ui.model

import com.ctaceks.settings.domain.model.Theme
import com.ctaceks.tasks.domain.model.TodoItem
import com.ctaceks.tasks.ui.TasksScreen


/**
 * Contains info about tasks ui actions on [TasksScreen]
 */
sealed class TasksAction {
    object CreateRoom : TasksAction()
    data class JoinTheRoom(val roomId: String) : TasksAction()
    object SignOut : TasksAction()
    object ShowSettings : TasksAction()
    data class UpdateTheme(val theme: Theme) : TasksAction()
}
