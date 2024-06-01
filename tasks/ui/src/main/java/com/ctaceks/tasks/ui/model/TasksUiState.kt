package com.ctaceks.tasks.ui.model

import com.ctaceks.settings.domain.model.Theme
import com.ctaceks.tasks.domain.model.TodoItem
import com.ctaceks.tasks.ui.TasksScreen

/**
 * Main state of [TasksScreen]
 */
data class TasksUiState(
    val isRefreshing: Boolean = false,
    val doneVisible: Boolean = true,
    val selectedTheme: Theme = Theme.SYSTEM,
    val tasks: List<TodoItem> = emptyList()
)
