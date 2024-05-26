package com.ctaceks.core.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ctaceks.core.ui.theme.ExtendedTheme

@Composable
fun TextInputComponent(
    modifier: Modifier,
    value: String,
    updateValue: (String) -> Unit,
    updateIsContainerError: (Boolean) -> Unit,
    isContainerError: Boolean,
    label: String = "",
    placeholder: String = "",
) {
    OutlinedTextField(
        value = value,
        onValueChange = {
            updateValue(it)
            updateIsContainerError(false)
        },
        isError = isContainerError,
        label = { Text(label) },
        placeholder = { Text(placeholder) },
        modifier = modifier
            .fillMaxWidth(0.8f)
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(28),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = ExtendedTheme.colors.backSecondary,
            errorContainerColor = ExtendedTheme.colors.backElevated
        ),
    )
}