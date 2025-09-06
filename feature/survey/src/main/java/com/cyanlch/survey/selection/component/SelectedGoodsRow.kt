package com.cyanlch.survey.selection.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.cyanlch.designsystem.HeightSpacer
import com.cyanlch.designsystem.R
import com.cyanlch.designsystem.WidthSpacer
import com.cyanlch.designsystem.ui.HGColors
import com.cyanlch.designsystem.ui.HGTypography
import com.cyanlch.survey.model.SelectedGoods
import com.cyanlch.survey.selection.GoodsSelectionScreen

@Composable
internal fun SelectedGoodsRow(state: GoodsSelectionScreen.State) {
    if (state.selectedGoods.isEmpty()) return
    HeightSpacer(16)
    LazyRow {
        items(state.selectedGoods) { goods ->
            SelectedGoodsItem(
                goods = goods,
                modifier = Modifier.size(88.dp),
                onCloseClick = { state.onToggleGoods(goods) },
            )
            WidthSpacer(4)
        }
    }
}

@Composable
private fun SelectedGoodsItem(
    modifier: Modifier = Modifier,
    goods: SelectedGoods,
    onCloseClick: () -> Unit,
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .border(
                width = 1.dp,
                color = HGColors.iconDark,
                shape = RoundedCornerShape(8.dp),
            ),
    ) {
        AsyncImage(
            model = goods.imageUrl,
            contentDescription = goods.name,
        )

        Box(
            modifier = Modifier
                .matchParentSize()
                .background(
                    Brush.verticalGradient(
                        colorStops = arrayOf(
                            0.0f to Color.Transparent,
                            0.5f to Color.Transparent,
                            1.0f to Color.Black,
                        ),
                    ),
                ),
        )
        Text(
            text = goods.name,
            color = Color.White,
            style = HGTypography.caption11SemiBold,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 6.dp)
                .padding(horizontal = 6.dp),
        )

        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .clickable(onClick = onCloseClick)
                .padding(8.dp)
                .size(20.dp)
                .background(color = HGColors.iconDark, shape = CircleShape),
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_close_8),
                contentDescription = "Close",
                modifier = Modifier
                    .size(12.dp)
                    .align(Alignment.Center),
            )
        }
    }
}
