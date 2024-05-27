package com.ctaceks.tasks.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ctaceks.auth.domain.AuthRepository
import com.ctaceks.core.di.FeatureScope
import com.ctaceks.settings.domain.model.Theme
import com.ctaceks.settings.domain.settings.AppSettingsMutableProvider
import com.ctaceks.tasks.domain.model.TodoItem
import com.ctaceks.tasks.domain.repo.TodoItemsRepository
import com.ctaceks.tasks.ui.model.TasksAction
import com.ctaceks.tasks.ui.model.TasksEvent
import com.ctaceks.tasks.ui.model.TasksUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * An intermediate layer between tasks ui and tasks logic
 *
 * Uses [AuthRepository] to control user
 *
 * Uses [TodoItemsRepository] for getting and editing [TodoItem]s
 */
@FeatureScope
class ChooseRoomViewModel @Inject constructor(
    private val authRepo: AuthRepository,
    private val todoRepo: TodoItemsRepository,
    private val settingsProvider: AppSettingsMutableProvider
): ViewModel() {
    private val _uiState = MutableStateFlow(TasksUiState())
    val uiState = _uiState.asStateFlow()

    init {
        setupViewModel()
    }

    private val _uiEvent = Channel<TasksEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onAction(action: TasksAction) {
        when(action) {
            is TasksAction.ShowSettings -> viewModelScope.launch { _uiEvent.send(TasksEvent.ShowSettings) }
            is TasksAction.CreateRoom -> createRoom()
            is TasksAction.JoinTheRoom -> joinTheRoom()
            is TasksAction.SignOut -> signOut()
            is TasksAction.UpdateTheme -> updateTheme(action.theme)
        }
    }

    private fun setupViewModel() {
        viewModelScope.launch {
            combine(
                todoRepo.todoItems(),
                todoRepo.doneVisible(),
                settingsProvider.settingsFlow()
            ) { tasks, doneVisible, settings ->
                val newTasks = when(doneVisible) {
                    true -> tasks
                    else -> tasks.filter { !it.isDone }
                }.sortedBy { it.createdAt }
                Triple(doneVisible, newTasks, settings)
            }.collectLatest { triple ->
                _uiState.update {
                    uiState.value.copy(
                        doneVisible = triple.first,
                        tasks = triple.second,
                        selectedTheme = triple.third.theme
                    )
                }
            }
        }
    }

    private fun createRoom() {
        viewModelScope.launch {
            _uiEvent.send(TasksEvent.CreateRoom)
        }
    }

    private fun joinTheRoom() {
        viewModelScope.launch {
            _uiEvent.send(TasksEvent.JoinTheRoom)
        }
    }

    private fun updateTheme(theme: Theme) {
        viewModelScope.launch {
            settingsProvider.updateTheme(theme)
        }
    }

    private fun signOut() {
        viewModelScope.launch {
            launch(Dispatchers.IO) {
                todoRepo.pushTodoItems()
            }
            delay(100)
            authRepo.signOut()
            todoRepo.clearTodoItems()
            _uiEvent.send(TasksEvent.SignOut)
        }
    }
}
