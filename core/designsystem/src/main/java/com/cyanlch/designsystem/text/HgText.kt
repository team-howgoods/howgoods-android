package com.cyanlch.designsystem.text

import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.isSpecified
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.cyanlch.designsystem.ui.HGTheme

enum class HgTextTone {
    Unspecified,
    Default,
    Alternative,
    Assistive,
    Inverse,
    Warning,
    Success,
    BrandPrimary,
    BrandSecondary,
}

@Composable
fun HgText(
    text: String,
    style: TextStyle,
    modifier: Modifier = Modifier,
    tone: HgTextTone = HgTextTone.Default,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    overflow: TextOverflow = TextOverflow.Clip,
    textAlign: TextAlign? = null,
    softWrap: Boolean = true,
    styleOverride: TextStyle = TextStyle.Default,
    onTextLayout: (TextLayoutResult) -> Unit = {},
) {
    val base = LocalTextStyle.current
        .merge(style)
        .merge(styleOverride)

    val color = if (base.color.isSpecified) {
        base.color
    } else {
        when (tone) {
            HgTextTone.Unspecified -> Color.Unspecified
            HgTextTone.Default -> HGTheme.tokens.textDefault
            HgTextTone.Alternative -> HGTheme.tokens.textAlternative
            HgTextTone.Assistive -> HGTheme.tokens.textAssistive
            HgTextTone.Inverse -> HGTheme.tokens.bgDefault
            HgTextTone.Warning -> HGTheme.tokens.warning
            HgTextTone.Success -> HGTheme.tokens.success
            HgTextTone.BrandPrimary -> HGTheme.tokens.brandPrimary
            HgTextTone.BrandSecondary -> HGTheme.tokens.brandSecondary
        }
    }
    Text(
        text = text,
        modifier = modifier,
        style = base.copy(color = color),
        maxLines = maxLines,
        minLines = minLines,
        overflow = overflow,
        textAlign = textAlign,
        softWrap = softWrap,
        onTextLayout = onTextLayout,
    )
}

@Composable
fun HgText(
    text: AnnotatedString,
    style: TextStyle,
    modifier: Modifier = Modifier,
    tone: HgTextTone = HgTextTone.Default,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    overflow: TextOverflow = TextOverflow.Clip,
    textAlign: TextAlign? = null,
    softWrap: Boolean = true,
    styleOverride: TextStyle = TextStyle.Default,
    inlineContent: Map<String, InlineTextContent> = emptyMap(),
    onTextLayout: (TextLayoutResult) -> Unit = {},
) {
    val base = LocalTextStyle.current
        .merge(style)
        .merge(styleOverride)

    val color = if (base.color.isSpecified) {
        base.color
    } else {
        when (tone) {
            HgTextTone.Unspecified -> Color.Unspecified
            HgTextTone.Default -> HGTheme.tokens.textDefault
            HgTextTone.Alternative -> HGTheme.tokens.textAlternative
            HgTextTone.Assistive -> HGTheme.tokens.textAssistive
            HgTextTone.Inverse -> HGTheme.tokens.bgDefault
            HgTextTone.Warning -> HGTheme.tokens.warning
            HgTextTone.Success -> HGTheme.tokens.success
            HgTextTone.BrandPrimary -> HGTheme.tokens.brandPrimary
            HgTextTone.BrandSecondary -> HGTheme.tokens.brandSecondary
        }
    }
    Text(
        text = text,
        modifier = modifier,
        style = base.copy(color = color),
        inlineContent = inlineContent,
        maxLines = maxLines,
        minLines = minLines,
        overflow = overflow,
        textAlign = textAlign,
        softWrap = softWrap,
        onTextLayout = onTextLayout,
    )
}
