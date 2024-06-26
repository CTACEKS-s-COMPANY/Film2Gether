package com.ctaceks.tasks.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ctaceks.core.ui.components.MultiSelector
import com.ctaceks.core.ui.theme.ExtendedTheme
import com.ctaceks.core.ui.theme.Red
import com.ctaceks.core.ui.theme.TodoAppTheme
import com.ctaceks.settings.domain.model.Theme
import com.ctaceks.tasks.ui.R
import com.ctaceks.tasks.ui.model.TasksAction
import com.ctaceks.tasks.ui.utils.toText

@Composable
fun ColumnScope.SettingsBottomSheetContent(
    appTheme: Theme,
    onAction: (TasksAction) -> Unit
) {
    val border = BorderStroke(1.dp, ExtendedTheme.colors.supportSeparator)
    val themeOptions = listOf(
        stringResource(R.string.theme_light),
        stringResource(R.string.theme_system),
        stringResource(R.string.theme_dark)
    )

    Text(
        text = stringResource(R.string.theme),
        color = ExtendedTheme.colors.labelPrimary,
        style = ExtendedTheme.typography.titleSmall
    )

    Spacer(modifier = Modifier.height(12.dp))

    MultiSelector(
        options = themeOptions,
        selectedOption = appTheme.toText(),
        onOptionSelect = {
            val newTheme = when(it) {
                themeOptions[0] -> Theme.LIGHT
                themeOptions[2] -> Theme.DARK
                else -> Theme.SYSTEM
            }

            onAction(TasksAction.UpdateTheme(newTheme))
        },
        modifier = Modifier
            .border(border, CircleShape)
            .fillMaxWidth(0.9f)
    )

    Divider(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        color = ExtendedTheme.colors.supportSeparator
    )

    OutlinedButton(
        onClick = { onAction(TasksAction.SignOut) },
        modifier = Modifier
            .heightIn(48.dp, 64.dp)
            .fillMaxWidth(0.9f),
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = Red
        ),
        border = border
    ) {
        Text(
            text = stringResource(R.string.sign_out),
            style = ExtendedTheme.typography.button
        )
    }
}

@Preview
@Composable
private fun SettingsBottomSheetContentPreview() {
    var theme by remember { mutableStateOf(Theme.LIGHT) }

    TodoAppTheme(
        darkTheme = theme == Theme.DARK
    ) {
        Box(
            modifier = Modifier
                .background(ExtendedTheme.colors.backPrimary)
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SettingsBottomSheetContent(
                    appTheme = theme,
                    onAction = {
                        when(it) {
                            is TasksAction.UpdateTheme -> {
                                theme = it.theme
                            }
                            else -> Unit
                        }
                    }
                )
            }
        }
    }
}