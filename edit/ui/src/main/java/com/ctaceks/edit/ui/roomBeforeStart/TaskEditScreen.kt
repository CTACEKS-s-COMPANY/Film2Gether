package com.ctaceks.edit.ui.roomBeforeStart

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ctaceks.core.ui.components.TodoBottomSheetLayout
import com.ctaceks.core.ui.components.rememberTodoBottomSheetState
import com.ctaceks.core.ui.theme.ExtendedTheme
import com.ctaceks.core.ui.theme.TodoAppTheme
import com.ctaceks.edit.ui.roomBeforeStart.components.TaskEditUiEventHandler
import com.ctaceks.edit.ui.roomBeforeStart.model.TaskEditAction
import com.ctaceks.edit.ui.roomBeforeStart.model.TaskEditEvent
import com.ctaceks.edit.ui.roomBeforeStart.model.TaskEditUiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

/**
 * Screen to create/edit task
 */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TaskEditScreen(
    uiState: TaskEditUiState,
    uiEvent: Flow<TaskEditEvent>,
    onAction: (TaskEditAction) -> Unit,
    onNavigateUp: () -> Unit,
    onSave: () -> Unit,
    onStart: () -> Unit,
) {
    val listState = rememberLazyListState()
    val sheetState = rememberTodoBottomSheetState()

    TaskEditUiEventHandler(uiEvent, onNavigateUp, onSave, sheetState)

    TodoBottomSheetLayout(
        sheetContent = {
            
        },
        sheetState = sheetState
    ) {
        val topBarElevation by animateDpAsState(
            if (listState.canScrollBackward) 8.dp else 0.dp,
            label = "top bar elevation"
        )

        Scaffold(
            containerColor = ExtendedTheme.colors.backPrimary
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Text("GOpa")
                Button(onClick = onStart) { }
            }
        }
    }
}

@Preview
@Composable
private fun TaskEditScreenPreview() {
    TodoAppTheme {
        TaskEditScreen(
            uiState = TaskEditUiState(),
            uiEvent = emptyFlow(),
            onAction = {},
            onNavigateUp = {},
            onSave = {},
            onStart = {}
        )
    }
}