package com.cyanlch.designsystem.icons

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cyanlch.designsystem.R
import com.cyanlch.designsystem.ui.HGTheme

@Composable
fun HgBookmarkBadgeIcon(
    modifier: Modifier = Modifier,
    size: Int = 24,
    checked: Boolean,
) {
    val colors = HGTheme.colors
    val extras = HGTheme.extras

    val fillTint = when {
        checked  -> colors.primary
        else     -> extras.lineDefault
    }

    Box(modifier.size(size.dp)) {
        Icon(
            painter = painterResource(R.drawable.ic_bookmark_fill_24),
            contentDescription = null,
            tint = fillTint,
            modifier = Modifier.matchParentSize()
        )
        Icon(
            painter = painterResource(R.drawable.ic_bookmark_stroke_24),
            contentDescription = null,
            tint = Color.Unspecified,
            modifier = Modifier.matchParentSize(),
        )
    }
}

@Preview
@Composable
fun HgBookmarkIconPreview() {
    Column {
        HGTheme(darkTheme = false) {
            HgBookmarkBadgeIcon(checked = false)
            HgBookmarkBadgeIcon(checked = true)
        }
        HGTheme(darkTheme = true) {
            HgBookmarkBadgeIcon(checked = false)
            HgBookmarkBadgeIcon(checked = true)
        }
    }
}
