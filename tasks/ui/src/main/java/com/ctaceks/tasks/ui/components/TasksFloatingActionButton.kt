package com.ctaceks.tasks.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ctaceks.core.ui.theme.Blue
import com.ctaceks.core.ui.theme.ExtendedTheme
import com.ctaceks.core.ui.theme.TodoAppTheme
import com.ctaceks.core.ui.theme.White
import com.ctaceks.tasks.ui.model.TasksAction

@Composable
fun TasksFloatingActionButton(
    onAction: (TasksAction) -> Unit
) {
    FloatingActionButton(
        onClick = { onAction(TasksAction.CreateTask) },
        containerColor = Blue,
        contentColor = White
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = null
        )
    }
}

@Preview
@Composable
private fun TasksFloatingActionButtonPreview() {
    TodoAppTheme {
        Box(
            modifier = Modifier
                .background(ExtendedTheme.colors.backPrimary)
                .padding(16.dp)
        ) {
            TasksFloatingActionButton({})
        }
    }
}