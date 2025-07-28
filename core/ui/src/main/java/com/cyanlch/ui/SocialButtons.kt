package com.cyanlch.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import com.cyanlch.designsystem.CyanOutlinedButton
import com.cyanlch.designsystem.WidthSpacer

@Composable
fun SocialButton(
    modifier: Modifier = Modifier,
    text: String,
    isEnabled: Boolean,
    imagePainter: Painter,
    textColor: Color,
    backgroundColor: Color,
    onClick: () -> Unit,
) {
    CyanOutlinedButton(
        enabled = isEnabled,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            contentColor = textColor,
        ),
        content = @Composable {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.padding(vertical = 16.dp),
            ) {
                Image(
                    painter = imagePainter,
                    contentDescription = "Button Icon",
                )
                WidthSpacer(16)

                Text(
                    text = text,
                )
            }
        },
        modifier = modifier
            .fillMaxWidth(),
    )
}
