package com.cyanlch.designsystem.button

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cyanlch.designsystem.ui.HGTheme

@Composable
fun HgSolidButton(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    colors: ButtonColors = HgButtonDefaults.SolidButtonColors,
    contentPadding: PaddingValues = HgButtonDefaults.SolidButtonPadding,
    onClick: () -> Unit,
    content: @Composable RowScope.() -> Unit,
) {
    Button(
        shape = HgButtonDefaults.SolidButtonShape,
        enabled = enabled,
        colors = colors,
        contentPadding = contentPadding,
        onClick = onClick,
        content = content,
        modifier = modifier,
    )
}

object HgButtonDefaults {
    val SolidButtonPadding = PaddingValues(vertical = 14.dp, horizontal = 28.dp)
    val SolidButtonShape = RoundedCornerShape(8.dp)

    val SolidButtonColors: ButtonColors
        @Composable get() = ButtonDefaults.buttonColors(
            containerColor = HGTheme.colors.primary,
            contentColor = Color.White,
            disabledContainerColor = HGTheme.extras.lineDefault,
            disabledContentColor = Color.White,
        )
}

@Preview(
    name = "Hg Buttons - Light",
    showBackground = true,
)
@Composable
fun PreviewHgButtonsLight() {
    HGTheme(darkTheme = false) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            HgSolidButton(onClick = {}) {
                Text("Solid Button (Primary)")
            }
            HgSolidButton(
                enabled = false,
                onClick = {},
            ) {
                Text("Disabled Solid")
            }
        }
    }
}

@Preview(
    name = "Hg Buttons - Dark",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
)
@Composable
fun PreviewHgButtonsDark() {
    PreviewHgButtonsLight()
}
