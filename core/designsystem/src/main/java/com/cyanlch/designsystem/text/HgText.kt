package com.cyanlch.designsystem.text

import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.isSpecified
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.cyanlch.designsystem.ui.HGTheme
import com.cyanlch.designsystem.ui.LocalHGColors

enum class HgTextVariant {
    Display,
    Headline,
    TitleLarge,
    Title,
    Body,
    Label,
}

enum class HgTextTone {
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
private fun variantToStyle(variant: HgTextVariant): TextStyle = when (variant) {
    HgTextVariant.Display -> MaterialTheme.typography.displaySmall
    HgTextVariant.Headline -> MaterialTheme.typography.headlineSmall
    HgTextVariant.TitleLarge -> MaterialTheme.typography.titleLarge
    HgTextVariant.Title -> MaterialTheme.typography.titleMedium
    HgTextVariant.Body -> MaterialTheme.typography.bodyMedium
    HgTextVariant.Label -> MaterialTheme.typography.labelMedium
}

@Composable
private fun toneColor(tone: HgTextTone) = with(LocalHGColors.current) {
    when (tone) {
        HgTextTone.Default -> textDefault
        HgTextTone.Alternative -> textAlternative
        HgTextTone.Assistive -> textAssistive
        HgTextTone.Inverse -> bgDefault
        HgTextTone.Warning -> warning
        HgTextTone.Success -> success
        HgTextTone.BrandPrimary -> brandPrimary
        HgTextTone.BrandSecondary -> brandSecondary
    }
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
    onTextLayout: (TextLayoutResult) -> Unit = {},
) {
    val base = LocalTextStyle.current
        .merge(style)
        .merge(styleOverride)

    val color = if (base.color.isSpecified) {
        base.color
    } else {
        when (tone) {
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
