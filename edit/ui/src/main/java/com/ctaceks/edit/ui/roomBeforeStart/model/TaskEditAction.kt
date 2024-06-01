package com.ctaceks.edit.ui.roomBeforeStart.model

import com.ctaceks.tasks.domain.model.Priority

/**
 * Contains info about edit ui actions
 */
sealed class TaskEditAction {
    object NavigateUp: TaskEditAction()
}
