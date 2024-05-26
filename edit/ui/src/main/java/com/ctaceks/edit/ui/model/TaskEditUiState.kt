package com.ctaceks.edit.ui.model

import com.ctaceks.edit.ui.utils.tomorrowLocalDateTime
import com.ctaceks.tasks.domain.model.Priority
import java.time.LocalDateTime

/**
 * Main state of edit screen
 */
data class TaskEditUiState(
    val description: String = "",
    val priority: Priority = Priority.COMMON,
    val deadline: LocalDateTime = tomorrowLocalDateTime,
    val isDeadlineVisible: Boolean = false,
    val isEditing: Boolean = false
) {
    val isDeleteEnabled: Boolean
        get() = description.isNotBlank() || isEditing
}
