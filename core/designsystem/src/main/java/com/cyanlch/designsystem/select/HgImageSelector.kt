package com.cyanlch.designsystem.select

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.cyanlch.designsystem.R
import com.cyanlch.designsystem.ui.HGTheme

@Composable
fun HgImageSelector(
    painter: Painter,
    contentDescription: String?,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    imageSize: ImageSelectorSize = ImageSelectorSize.Small,
    contentScale: ContentScale = ContentScale.Crop,
    borderWidth: Dp = 5.dp,
    caption: String? = null,
    captionStyle: TextStyle = MaterialTheme.typography.labelLarge,
    captionColor: Color = Color.White,
    captionPadding: PaddingValues = PaddingValues(
        horizontal = 16.dp,
        vertical = 8.dp
    ),
    gradientCoverage: Float = 0.40f,
    gradientAlpha: Float = 0.80f,
    placeholderColor: Color = Color(0xFFD9D9D9)
) {
    val colors = ImageSelectorDefaults.colors(selected)
    val border = if (selected) BorderStroke(borderWidth, colors.border) else null
    val shape = ImageSelectorDefaults.geometry(imageSize).shape
    Box(
        modifier = modifier
            .clip(shape)
            .then(if (border != null) Modifier.border(border, shape) else Modifier)
            .background(placeholderColor)
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

        if (!caption.isNullOrEmpty()) {
            val stop = (1f - gradientCoverage).coerceIn(0f, 1f)

            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(
                        Brush.verticalGradient(
                            colorStops = arrayOf(
                                0.0f to Color.Transparent,
                                stop  to Color.Transparent,
                                1.0f to Color.Black.copy(alpha = gradientAlpha)
                            )
                        )
                    )
            )

            Text(
                text = caption,
                color = captionColor,
                style = captionStyle,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(captionPadding)
            )
        }
    }
}

data class ImageSelectorColors(
    val border: Color,
)

enum class ImageSelectorSize(val border: Dp) {
    Small(8.dp),
    Large(16.dp),
}

data class ImageSelectorGeometry(
    val shape: Shape,
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

    fun geometry(size: ImageSelectorSize): ImageSelectorGeometry = ImageSelectorGeometry(
        shape = RoundedCornerShape(size.border)
    )
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun HgImageSelectorSelectedPreview() {
    HGTheme {
        HgImageSelector(
            painter = painterResource(R.drawable.sample_image),
            contentDescription = "샘플 이미지",
            selected = true,
            onClick = {},
            caption = "샘플 캡션",
            modifier = Modifier.size(108.dp),
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun HgImageSelectorPreview() {
    HGTheme {
        HgImageSelector(
            painter = painterResource(R.drawable.sample_image),
            contentDescription = "샘플 이미지",
            selected = false,
            onClick = {},
            caption = "샘플 캡션이 길어",
            modifier = Modifier.size(108.dp),
        )
    }
}


@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun HgImageSelectorNonCaptionPreview() {
    HGTheme {
        HgImageSelector(
            painter = painterResource(R.drawable.sample_image),
            contentDescription = "샘플 이미지",
            selected = false,
            onClick = {},
            modifier = Modifier.size(108.dp),
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun HgImageSelectorNonCaptionLargePreview() {
    HGTheme {
        HgImageSelector(
            painter = painterResource(R.drawable.sample_image),
            contentDescription = "샘플 이미지",
            selected = false,
            imageSize = ImageSelectorSize.Large,
            onClick = {},
            modifier = Modifier.size(200.dp),
        )
    }
}

