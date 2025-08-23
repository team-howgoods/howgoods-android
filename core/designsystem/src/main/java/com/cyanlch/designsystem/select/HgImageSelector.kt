package com.cyanlch.designsystem.select

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.cyanlch.designsystem.R
import com.cyanlch.designsystem.ui.HGColors
import com.cyanlch.designsystem.ui.HGTheme

@Composable
fun HgImageSelector(
    painter: Painter,
    contentDescription: String?,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = RoundedCornerShape(12.dp),
    contentScale: ContentScale = ContentScale.Crop,
    borderWidth: Dp = 2.dp,
) {
    val colors = ImageSelectorDefaults.colors(selected)
    val border = if (selected) BorderStroke(borderWidth, colors.border) else null
    Box(
        modifier = modifier
            .clip(shape)
            .then(if (border != null) Modifier.border(border, shape) else Modifier)
            .background(HGColors.gray200)
            .clickable(
                enabled = enabled,
                role = Role.Checkbox,
                onClick = onClick,
            )
            .semantics { this.selected = selected },
    ) {
        Image(
            painter = painter,
            contentDescription = contentDescription,
            contentScale = contentScale,
            modifier = Modifier.matchParentSize(),
        )
    }
}

data class ImageSelectorColors(
    val border: Color,
)

object ImageSelectorDefaults {
    @Composable
    fun colors(selected: Boolean): ImageSelectorColors {
        val colors = HGTheme.colors
        return if (selected) {
            ImageSelectorColors(
                border = colors.primary,
            )
        } else {
            ImageSelectorColors(
                border = Color.Transparent,
            )
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun HgImageSelectorSelectedPreview() {
    HGTheme {
        HgImageSelector(
            painter = painterResource(R.drawable.ic_bookmark_stroke_24),
            contentDescription = "샘플 이미지",
            selected = true,
            onClick = {},
            modifier = Modifier.size(50.dp),
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun HgImageSelectorPreview() {
    HGTheme {
        HgImageSelector(
            painter = painterResource(R.drawable.ic_bookmark_stroke_24),
            contentDescription = "샘플 이미지",
            selected = false,
            onClick = {},
            modifier = Modifier.size(50.dp),
        )
    }
}
