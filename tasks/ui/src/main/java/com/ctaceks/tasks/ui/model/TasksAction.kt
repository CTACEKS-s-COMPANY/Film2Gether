package com.ctaceks.tasks.ui.model

import com.ctaceks.settings.domain.model.Theme
import com.ctaceks.tasks.domain.model.TodoItem
import com.ctaceks.tasks.ui.TasksScreen


/**
 * Contains info about tasks ui actions on [TasksScreen]
 */
sealed class TasksAction {
    object CreateTask : TasksAction()
    object CreateRoom : TasksAction()
    data class JoinTheRoom(val roomId: String) : TasksAction()
    data class UpdateTask(val todoItem: TodoItem) : TasksAction()
    data class DeleteTask(val todoItem: TodoItem) : TasksAction()
    data class EditTask(val todoItem: TodoItem) : TasksAction()
    data class UpdateDoneVisibility(val visible: Boolean) : TasksAction()
    object UndoAction : TasksAction()
    object ShowSettings : TasksAction()
    data class UpdateTheme(val theme: Theme) : TasksAction()
    object UpdateRequest : TasksAction()
    object RefreshTasks : TasksAction()
    object SignOut : TasksAction()
}
