package com.ctaceks.tasks.ui.components

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.work.WorkManager
import com.ctaceks.other.work.utils.SYNCHRONIZATION_WORK_TAG
import com.ctaceks.tasks.ui.R
import com.ctaceks.tasks.ui.model.TasksAction
import com.ctaceks.tasks.ui.model.TasksEvent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TasksUiEventHandler(
    uiEvent: Flow<TasksEvent>,
    onSignOut: () -> Unit,
    onCreateRoom: () -> Unit,
    onAction: (TasksAction) -> Unit,
    onJoinRoom: () -> Unit,
    snackbarHostState: SnackbarHostState,
    sheetState: ModalBottomSheetState
) {
    val context = LocalContext.current
    var launchedBefore by rememberSaveable { mutableStateOf(false) }
    /*LaunchedEffect(Unit) {
        if (!launchedBefore) {
            val workManager = WorkManager.getInstance(context)
            val workInfo = workManager
                .getWorkInfosByTag(SYNCHRONIZATION_WORK_TAG).get()

            if (workInfo.firstOrNull {!it.state.isFinished} == null)
                onAction(TasksAction.UpdateRequest)

            launchedBefore = true
        }
    }*/

    LaunchedEffect(Unit) {
        uiEvent.collect {
            when(it) {
                TasksEvent.ShowSettings -> {
                    if (!sheetState.isVisible)
                        sheetState.show()
                }
                is TasksEvent.CreateRoom -> onCreateRoom()
                is TasksEvent.JoinTheRoom -> onJoinRoom()
                TasksEvent.SignOut -> onSignOut()
            }
        }
    }
}
