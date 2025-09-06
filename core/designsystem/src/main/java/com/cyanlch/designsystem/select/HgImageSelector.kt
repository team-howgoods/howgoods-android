package com.cyanlch.designsystem.select

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.toggleable
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.cyanlch.designsystem.R
import com.cyanlch.designsystem.text.HgText
import com.cyanlch.designsystem.ui.HGTheme
import com.cyanlch.designsystem.ui.HGTypography

@Composable
fun HgImageSelector(
    painter: Painter,
    contentDescription: String?,
    selected: Boolean,
    onClick: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    imageSize: ImageSelectorSize = ImageSelectorSize.Small,
    contentScale: ContentScale = ContentScale.Crop,
    borderWidth: Dp = 5.dp,
    caption: String? = null,
    captionStyle: TextStyle = MaterialTheme.typography.labelLarge,
    captionColor: Color = Color.White,
    captionPadding: PaddingValues = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
    gradientCoverage: Float = 0.40f,
    gradientAlpha: Float = 0.80f,
    placeholderColor: Color = Color(0xFFD9D9D9),
    cnt: Int? = null,
) {
    HgImageSelectorLayout(
        selected = selected,
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        imageSize = imageSize,
        borderWidth = borderWidth,
        caption = caption,
        captionStyle = captionStyle,
        captionColor = captionColor,
        captionPadding = captionPadding,
        gradientCoverage = gradientCoverage,
        gradientAlpha = gradientAlpha,
        placeholderColor = placeholderColor,
        cnt = cnt,
    ) {
        Image(
            painter = painter,
            contentDescription = contentDescription,
            contentScale = contentScale,
            modifier = Modifier.matchParentSize(),
        )
    }
}

@Composable
fun HgImageSelector(
    imageUrl: Any?,
    contentDescription: String? = null,
    selected: Boolean,
    onClick: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    imageSize: ImageSelectorSize = ImageSelectorSize.Small,
    contentScale: ContentScale = ContentScale.Crop,
    borderWidth: Dp = 5.dp,
    caption: String? = null,
    captionStyle: TextStyle = MaterialTheme.typography.labelLarge,
    captionColor: Color = Color.White,
    captionPadding: PaddingValues = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
    gradientCoverage: Float = 0.40f,
    gradientAlpha: Float = 0.80f,
    placeholderColor: Color = Color(0xFFD9D9D9),
    placeholder: Painter? = null,
    error: Painter? = null,
    fallback: Painter? = null,
    crossfade: Boolean = true,
    cnt: Int? = null,
) {
    HgImageSelectorLayout(
        selected = selected,
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        imageSize = imageSize,
        borderWidth = borderWidth,
        caption = caption,
        captionStyle = captionStyle,
        captionColor = captionColor,
        captionPadding = captionPadding,
        gradientCoverage = gradientCoverage,
        gradientAlpha = gradientAlpha,
        placeholderColor = placeholderColor,
        cnt = cnt,
    ) {
        val request = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .crossfade(crossfade)
            .build()
        AsyncImage(
            model = request,
            contentDescription = contentDescription,
            placeholder = placeholder,
            error = error,
            fallback = fallback,
            contentScale = contentScale,
            modifier = Modifier.matchParentSize(),
        )
    }
}

@Composable
private fun HgImageSelectorLayout(
    selected: Boolean,
    onClick: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    imageSize: ImageSelectorSize = ImageSelectorSize.Small,
    borderWidth: Dp = 5.dp,
    caption: String? = null,
    captionStyle: TextStyle = MaterialTheme.typography.labelLarge,
    captionColor: Color = Color.White,
    captionPadding: PaddingValues = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
    gradientCoverage: Float = 0.40f,
    gradientAlpha: Float = 0.80f,
    placeholderColor: Color = Color(0xFFD9D9D9),
    cnt: Int? = null,
    imageContent: @Composable BoxScope.() -> Unit,
) {
    val colors = ImageSelectorDefaults.colors(selected)
    val shape = ImageSelectorDefaults.geometry(imageSize).shape
    val border = if (selected) BorderStroke(borderWidth, colors.border) else null
    val stop = (1f - gradientCoverage).coerceIn(0f, 1f)

    Box(
        modifier = modifier
            .clip(shape)
            .then(if (border != null) Modifier.border(border, shape) else Modifier)
            .background(placeholderColor)
            .toggleable(
                value = selected,
                enabled = enabled,
                role = Role.Checkbox,
                onValueChange = onClick,
            )
            .semantics { this.selected = selected },
    ) {
        imageContent()

        if (!caption.isNullOrEmpty()) {
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(
                        Brush.verticalGradient(
                            colorStops = arrayOf(
                                0.0f to Color.Transparent,
                                stop to Color.Transparent,
                                1.0f to Color.Black.copy(alpha = gradientAlpha),
                            ),
                        ),
                    ),
            )
            Text(
                text = caption,
                color = captionColor,
                style = captionStyle,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(captionPadding),
            )
        }

        if (cnt != null && selected) {
            Text(
                text = "$cnt",
                textAlign = TextAlign.Center,
                color = Color.White,
                style = HGTypography.body1SemiBold,
                modifier = Modifier
                    .padding(start = 12.dp, top = 12.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .defaultMinSize(26.dp)
                    .background(HGTheme.colors.primary)
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
        shape = RoundedCornerShape(size.border),
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

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun HgImageSelectorCntTextPreview() {
    HGTheme {
        HgImageSelector(
            painter = painterResource(R.drawable.sample_image),
            contentDescription = "샘플 이미지",
            selected = true,
            imageSize = ImageSelectorSize.Large,
            onClick = {},
            modifier = Modifier.size(200.dp),
            cnt = 20,
        )
    }
}
