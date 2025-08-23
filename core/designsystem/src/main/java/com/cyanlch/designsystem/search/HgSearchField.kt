package com.cyanlch.designsystem.search

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.cyanlch.designsystem.R
import com.cyanlch.designsystem.WidthSpacer
import com.cyanlch.designsystem.icons.CloseButtonBackground
import com.cyanlch.designsystem.icons.HgCloseButton
import com.cyanlch.designsystem.ui.HGColors
import com.cyanlch.designsystem.ui.HGTheme

@Composable
fun HgSearchField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "검색어를 입력하세요",
    enabled: Boolean = true,
    onSearch: (() -> Unit)? = null,
    onClear: (() -> Unit)? = { onValueChange("") },
    leadingIcon: Painter = painterResource(R.drawable.ic_search_20),
) {
    val kOpt = KeyboardOptions.Default.copy(imeAction = ImeAction.Search)
    val kAct = KeyboardActions(onSearch = { onSearch?.invoke() })

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .heightIn(HgSearchFieldDefaults.minHeight)
            .clip(HgSearchFieldDefaults.shape)
            .background(HgSearchFieldDefaults.container)
            .border(
                BorderStroke(HgSearchFieldDefaults.borderWidth, HgSearchFieldDefaults.border),
                HgSearchFieldDefaults.shape,
            )
            .padding(horizontal = HgSearchFieldDefaults.horizontalPadding)
            .semantics { contentDescription = "검색창" },
    ) {
        Icon(
            painter = leadingIcon,
            contentDescription = null,
            tint = HgSearchFieldDefaults.iconTint,
        )
        WidthSpacer(8)

        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            enabled = enabled,
            singleLine = true,
            textStyle = HgSearchFieldDefaults.textStyle(),
            keyboardOptions = kOpt,
            keyboardActions = kAct,
            cursorBrush = SolidColor(HGTheme.colors.primary),
            modifier = Modifier
                .weight(1f),
        ) { inner ->
            if (value.isEmpty()) {
                Text(
                    text = placeholder,
                    style = HgSearchFieldDefaults.placeholderStyle(),
                    color = HGColors.gray500,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
            inner()
        }

        if (value.isNotEmpty() && onClear != null) {
            WidthSpacer(8)
            HgCloseButton(
                modifier = Modifier,
                onClick = { onClear() },
                enabled = enabled,
                background = CloseButtonBackground.Delete,
            )
        }
    }
}

object HgSearchFieldDefaults {
    val minHeight: Dp = 44.dp
    val shape = RoundedCornerShape(8.dp)
    val container = HGColors.white
    val border: Color @Composable get() = HGTheme.extras.lineAlternative
    val borderWidth: Dp = 2.dp
    val horizontalPadding: Dp = 12.dp
    val iconTint = HGColors.gray500

    @Composable
    fun textStyle(): TextStyle = HGTheme.typography.bodyMedium

    @Composable
    fun placeholderStyle(): TextStyle = HGTheme.typography.bodyMedium
}

@Preview(name = "SearchField - Light", showBackground = true)
@Composable
private fun PreviewSearchFieldLight() {
    HGTheme(darkTheme = false) {
        HgSearchField(value = "", onValueChange = {})
    }
}

@Preview(
    name = "SearchField - Dark",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
)
@Composable
private fun PreviewSearchFieldDark() {
    HGTheme(darkTheme = true) {
        HgSearchField(value = "헌터헌터 인형", onValueChange = {})
    }
}
