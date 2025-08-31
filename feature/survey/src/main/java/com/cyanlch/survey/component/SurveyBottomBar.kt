package com.cyanlch.survey.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.cyanlch.designsystem.decoration.WhiteFadeBar
import com.cyanlch.designsystem.ui.LocalHGColors

@Composable
internal fun SurveyBottomBar(
    buttons: @Composable RowScope.() -> Unit,
) {
    Box(
        modifier = Modifier
            .background(LocalHGColors.current.bgDefault)
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(bottom = 16.dp),
    ) {
        Row {
            buttons()
        }
        WhiteFadeBar(
            horizontal = false,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .height(24.dp)
                .offset(y = (-24).dp)
                .zIndex(1f),
        )
    }
}
