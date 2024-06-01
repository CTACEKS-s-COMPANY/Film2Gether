package com.ctaceks.edit.ui.roomBeforeStart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ctaceks.core.di.FeatureScope
import com.ctaceks.edit.ui.roomBeforeStart.model.TaskEditAction
import com.ctaceks.edit.ui.roomBeforeStart.model.TaskEditEvent
import com.ctaceks.edit.ui.roomBeforeStart.model.TaskEditUiState
import com.ctaceks.tasks.domain.model.TodoItem
import com.ctaceks.tasks.domain.repo.TodoItemsRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * An intermediate layer between edit ui and tasks logic
 *
 * Uses [TodoItemsRepository] to work with task
 */
@FeatureScope
class RoomBeforeStartViewModel @Inject constructor(
    private val repo: TodoItemsRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(TaskEditUiState())
    val uiState = _uiState.asStateFlow()

    private val _uiEvent = Channel<TaskEditEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onAction(action: TaskEditAction) {
        when (action) {
            TaskEditAction.NavigateUp -> viewModelScope.launch {
                _uiEvent.send(TaskEditEvent.NavigateBack)
            }
        }
    }

}
