package com.cyanlch.designsystem.icons

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.cyanlch.designsystem.R
import com.cyanlch.designsystem.ui.HGColors
import com.cyanlch.designsystem.ui.HGTheme

enum class CloseButtonBackground(val color: Color) {
    Primary(HGColors.primary),
    Delete(HGColors.bgDelete),
}

@Composable
fun HgCloseButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    background: CloseButtonBackground = CloseButtonBackground.Primary,
    enabled: Boolean = true,
    size: Dp = 18.dp,
) {
    val colors = HGTheme.colors
    val extras = HGTheme.extras

    Surface(
        onClick = onClick,
        enabled = enabled,
        shape = CircleShape,
        color = background.color,
        contentColor = HGColors.white,
        modifier = modifier.size(size),
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_close_8),
            contentDescription = null,
            tint = HGColors.white,
            modifier = Modifier.padding(5.dp),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun HgCloseButtonPreview() {
    Row {
        HGTheme(darkTheme = false) {
            HgCloseButton(onClick = {})
            HgCloseButton(onClick = {}, background = CloseButtonBackground.Delete, enabled = false)
        }
        HGTheme(darkTheme = true) {
            HgCloseButton(onClick = {})
            HgCloseButton(onClick = {}, background = CloseButtonBackground.Delete, enabled = false)
        }
    }
}
