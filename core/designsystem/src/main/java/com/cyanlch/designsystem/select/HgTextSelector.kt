package com.cyanlch.designsystem.select

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cyanlch.designsystem.HeightSpacer
import com.cyanlch.designsystem.R
import com.cyanlch.designsystem.WidthSpacer
import com.cyanlch.designsystem.ui.HGColors
import com.cyanlch.designsystem.ui.HGTheme

@Composable
fun HgTextSelector(
    modifier: Modifier = Modifier,
    text: String,
    selected: Boolean,
    onClick: (Boolean) -> Unit,
    iconVisible: Boolean = true,
    enabled: Boolean = true,
) {
    val colors = HgTextSelectorDefaults.colors(selected)
    val shape = RoundedCornerShape(55.dp)

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .clip(shape)
            .background(colors.container)
            .border(BorderStroke((1.5).dp, colors.border), shape)
            .toggleable(
                value = selected,
                enabled = enabled,
                role = Role.Checkbox,
                onValueChange = onClick,
            )
            .padding(horizontal = 12.dp, vertical = 10.dp),
    ) {
        if (iconVisible) {
            Icon(
                painter = painterResource(R.drawable.ic_plus_12),
                contentDescription = null,
                tint = colors.icon,
            )
            WidthSpacer(2)
        }

        Text(
            text = text,
            color = colors.content,
            style = HGTheme.typography.labelLarge,
        )
    }
}

data class HgSelectorColors(
    val container: Color,
    val content: Color,
    val border: Color,
    val icon: Color,
)

object HgTextSelectorDefaults {
    @Composable
    fun colors(
        selected: Boolean,
    ): HgSelectorColors {
        return if (selected) {
            HgSelectorColors(
                container = HGColors.bgPrimary,
                content = HGColors.textDefault,
                border = HGColors.primary,
                icon = HGColors.primary,
            )
        } else {
            HgSelectorColors(
                container = HGColors.bgDefault,
                content = HGColors.textDefault,
                border = HGColors.lineDefault,
                icon = HGColors.textDefault,
            )
        }
    }
}

@Preview
@Composable
fun HgTextSelectorPreview() {
    HGTheme(darkTheme = false) {
        Column {
            HgTextSelector(text = "주술회전", selected = false, onClick = {})
            HeightSpacer(16)
            HgTextSelector(text = "주술회전", selected = true, onClick = {})
            HeightSpacer(16)
            HgTextSelector(
                text = "좋아하는 애니메이션이 없어요",
                selected = false,
                iconVisible = false,
                onClick = {},
            )
        }
    }
}
