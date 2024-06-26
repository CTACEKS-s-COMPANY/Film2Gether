package com.ctaceks.tasks.ui

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ctaceks.core.ui.components.ButtonComponent
import com.ctaceks.core.ui.components.TextInputComponent
import com.ctaceks.core.ui.components.TodoBottomSheetLayout
import com.ctaceks.core.ui.theme.Blue
import com.ctaceks.core.ui.theme.ExtendedTheme
import com.ctaceks.core.ui.theme.TodoAppTheme
import com.ctaceks.tasks.domain.model.Priority
import com.ctaceks.tasks.domain.model.TodoItem
import com.ctaceks.tasks.ui.components.SettingsBottomSheetContent
import com.ctaceks.tasks.ui.components.TasksTopAppBar
import com.ctaceks.tasks.ui.components.TasksUiEventHandler
import com.ctaceks.tasks.ui.model.TasksAction
import com.ctaceks.tasks.ui.model.TasksEvent
import com.ctaceks.tasks.ui.model.TasksUiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import java.time.LocalDateTime

/**
 * Main app screen for watching and editing all user [TodoItem]s
 */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TasksScreen(
    uiState: TasksUiState,
    uiEvent: Flow<TasksEvent>,
    onAction: (TasksAction) -> Unit,
    onCreateRoom: () -> Unit,
    onJoinRoom: () -> Unit,
    onSignOut: () -> Unit
) {
    val listState = rememberLazyListState()
    val snackbarHostState = remember { SnackbarHostState() }
    val sheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)

    TasksUiEventHandler(
        uiEvent = uiEvent,
        onAction = onAction,
        onSignOut = onSignOut,
        snackbarHostState = snackbarHostState,
        onCreateRoom = onCreateRoom,
        onJoinRoom = onJoinRoom,
        sheetState = sheetState
    )



    TodoBottomSheetLayout(
        sheetContent = {
            SettingsBottomSheetContent(uiState.selectedTheme, onAction)
        },
        sheetState = sheetState,
    ) {
        val topBarElevation by animateDpAsState(
            if (listState.canScrollBackward) 8.dp else 0.dp, label = "top bar elevation"
        )

        var roomId by remember { mutableStateOf("") }
        var isRoomIdError by remember { mutableStateOf(false) }

        Scaffold(topBar = {
            TasksTopAppBar(uiState.doneVisible, topBarElevation, onAction)
        }, snackbarHost = {
            SnackbarHost(snackbarHostState) {
                Snackbar(
                    snackbarData = it, actionColor = Blue
                )
            }
        },
            containerColor = ExtendedTheme.colors.backPrimary
        ) { paddingValues ->
            Box(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Bottom,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    TextInputComponent(
                        modifier = Modifier,
                        value = roomId,
                        updateValue = { roomId = it },
                        updateIsContainerError = { isRoomIdError = it },
                        isContainerError = isRoomIdError,
                        label = "Room id",
                        placeholder = "P0P4N3gr4"
                    )
                    ButtonComponent(
                        modifier = Modifier,
                        title = "Join the room",
                        onAction = {
                            if (roomId.isEmpty()) {
                                isRoomIdError = true
                            } else {
                                onAction(TasksAction.JoinTheRoom(roomId))
                            }
                        }
                    )
                    ButtonComponent(
                        modifier = Modifier,
                        title = "Create room",
                        onAction = {
                            onAction(TasksAction.CreateRoom)
                        }
                    )
                    Spacer(modifier = Modifier.height(50.dp))
                }
                /*PullRefreshIndicator(
                    refreshing = uiState.isRefreshing,
                    state = pullRefreshState,
                    modifier = Modifier.align(Alignment.TopCenter)
                )*/
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    val tasks = listOf(
        TodoItem("1", "Task 1", LocalDateTime.now(), Priority.HIGH, isDone = true),
        TodoItem("2", "Task 2", LocalDateTime.now().plusDays(1), Priority.LOW)
    )
    TodoAppTheme {
        TasksScreen(
            uiState = TasksUiState(tasks = tasks),
            uiEvent = emptyFlow(),
            onAction = {},
            onSignOut = {},
            onCreateRoom = {},
            onJoinRoom = {},
        )
    }
}