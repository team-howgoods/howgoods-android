package com.cyanlch.designsystem.decoration

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun WhiteFadeBar(
    modifier: Modifier = Modifier,
    horizontal: Boolean = false,
    reverse: Boolean = false
) {
    val stops = arrayOf(
        0.0f to Color(0xFFFFFFFF).copy(alpha=0f),
        1.0f to Color(0xFFFFFFFF),
    )
    val cs = if (reverse) stops.reversedArray() else stops
    val brush = if (horizontal)
        Brush.horizontalGradient(colorStops = cs)
    else
        Brush.verticalGradient(colorStops = cs)

    Box(
        modifier
            .fillMaxWidth()
            .background(brush)
    )
}

@Preview
@Composable
private fun WhiteFadeBarPreview() {
    Box(Modifier.background(Color.Green).height(120.dp)) {
        WhiteFadeBar(
            horizontal = false,
            modifier = Modifier.height(82.dp).align(Alignment.BottomCenter)
        )
    }
}