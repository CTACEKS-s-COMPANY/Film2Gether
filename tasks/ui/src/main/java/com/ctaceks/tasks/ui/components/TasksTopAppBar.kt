package com.ctaceks.tasks.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ctaceks.core.ui.theme.Blue
import com.ctaceks.core.ui.theme.ExtendedTheme
import com.ctaceks.core.ui.theme.TodoAppTheme
import com.ctaceks.tasks.ui.R
import com.ctaceks.tasks.ui.model.TasksAction

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TasksTopAppBar(
    doneVisible: Boolean,
    elevation: Dp,
    onAction: (TasksAction) -> Unit
) {
    TopAppBar(
        modifier = Modifier
            .shadow(elevation),
        title = {
            Text(
                text = stringResource(id = com.ctaceks.core.ui.R.string.app_name),
                style = ExtendedTheme.typography.title
            )
        },
        navigationIcon = {
            IconButton(
                onClick = {
                    onAction(TasksAction.ShowSettings)
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = null,
                )
            }
        },
        actions = {
/*            IconButton(
                onClick = {
                    onAction(TasksAction.UpdateDoneVisibility(!doneVisible))
                }
            ) {
                Icon(
                    painter = painterResource(
                        id = if (doneVisible) R.drawable.ic_baseline_visibility_24dp
                        else R.drawable.ic_baseline_visibility_off_24dp
                    ),
                    contentDescription = null,
                )
            }*/
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = ExtendedTheme.colors.backPrimary,
            navigationIconContentColor = ExtendedTheme.colors.labelPrimary,
            titleContentColor = ExtendedTheme.colors.labelPrimary,
            actionIconContentColor = Blue
        )
    )
}

@Preview
@Composable
private fun TasksTopAppBarPreview() {
    TodoAppTheme {
        TasksTopAppBar(
            doneVisible = true,
            elevation = 0.dp,
            onAction = {}
        )
    }
}