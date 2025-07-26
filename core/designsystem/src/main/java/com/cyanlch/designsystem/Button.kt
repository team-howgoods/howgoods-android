package com.cyanlch.designsystem

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CyanOutlinedButton(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    colors: ButtonColors = ButtonDefaults.outlinedButtonColors(),
    contentPadding: PaddingValues = SideButtonDefaults.OutlinedButtonPadding,
    content: @Composable RowScope.() -> Unit,
    onClick: () -> Unit,
) {
    OutlinedButton(
        shape = SideButtonDefaults.OutlinedButtonShape,
        enabled = enabled,
        colors = colors,
        border = BorderStroke(
            width = SideButtonDefaults.OutlinedButtonBorderWidth,
            color = Color.Black // TODO
        ),
        contentPadding = contentPadding,
        onClick = onClick,
        content = content,
        modifier = modifier
    )
}

object SideButtonDefaults {
    val OutlinedButtonBorderWidth = 1.dp
    val OutlinedButtonPadding = PaddingValues(16.dp)
    val OutlinedButtonShape = RoundedCornerShape(12.dp)
}