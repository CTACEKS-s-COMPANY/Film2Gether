package com.ctaceks.core.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ctaceks.core.ui.theme.ExtendedTheme

@Composable
fun ButtonComponent(
    modifier: Modifier,
    title: String,
    onAction: () -> Unit,
) {
    OutlinedButton(
        onClick = onAction,
        modifier = modifier
            .fillMaxWidth(0.8f)
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(28),
        colors = ButtonDefaults.buttonColors(
            containerColor = ExtendedTheme.colors.backSecondary,
            contentColor = ExtendedTheme.colors.labelPrimary
        ),
        contentPadding = PaddingValues(vertical = 16.dp, horizontal = 32.dp)
    ) {
        Text(
            text = title,
            style = ExtendedTheme.typography.titleSmall,
            color = ExtendedTheme.colors.labelPrimary
        )
    }
}