package com.cyanlch.survey.search

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.cyanlch.designsystem.HeightSpacer
import com.cyanlch.designsystem.button.HgSolidButton
import com.cyanlch.designsystem.search.HgSearchField
import com.cyanlch.designsystem.select.HgImageSelector
import com.cyanlch.designsystem.text.HgText
import com.cyanlch.designsystem.text.HgTextTone
import com.cyanlch.designsystem.ui.HGTheme
import com.cyanlch.designsystem.ui.HGTypography
import com.cyanlch.designsystem.ui.LocalHGColors
import com.cyanlch.survey.component.SurveyBottomBar
import com.cyanlch.ui.topbar.HgBasicTopBar
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.runtime.ui.Ui
import dagger.hilt.android.components.ActivityRetainedComponent
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map

@CircuitInject(GoodsSearchScreen::class, ActivityRetainedComponent::class)
class GoodsSearchUi : Ui<GoodsSearchScreen.State> {
    @Composable
    override fun Content(state: GoodsSearchScreen.State, modifier: Modifier) {
        val context = LocalContext.current
        LaunchedEffect(state.errorMessage) {
            state.errorMessage?.let {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                state.onErrorShown()
            }
        }

        HGTheme {
            Scaffold(
                topBar = {
                    HgBasicTopBar(onBackClick = state.onBack)
                },
                bottomBar = {
                    SurveyBottomBar {
                        HgSolidButton(
                            onClick = state.onBack,
                            modifier = Modifier
                                .weight(1f),
                        ) {
                            HgText(
                                text = "완료",
                                style = HGTypography.body2SemiBold,
                                tone = HgTextTone.Unspecified,
                            )
                        }
                    }
                },
            ) { inner ->
                GoodsSearchContent(
                    state = state,
                    modifier = modifier.padding(inner),
                )
            }
        }
    }
}

@Composable
private fun GoodsSearchContent(
    modifier: Modifier,
    state: GoodsSearchScreen.State,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(LocalHGColors.current.bgDefault)
            .padding(horizontal = 16.dp),
    ) {
        HeightSpacer(16)
        HgSearchField(
            value = state.query,
            onValueChange = state.onQueryChange,
            placeholder = "굿즈 이름을 입력해 주세요",
            modifier = Modifier.fillMaxWidth(),
        )
        HeightSpacer(16)
        GoodsGrid(state)
    }
}

@Composable
private fun GoodsGrid(state: GoodsSearchScreen.State) {
    val gridState = rememberLazyGridState()
    LaunchedEffect(gridState, state.canLoadMore) {
        snapshotFlow {
            val info = gridState.layoutInfo
            info.visibleItemsInfo.lastOrNull()?.index
        }
            .map { it ?: 0 }
            .distinctUntilChanged()
            .filter { state.canLoadMore && it >= state.items.lastIndex }
            .collectLatest { state.onLoadMore() }
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        state = gridState,
        modifier = Modifier.fillMaxSize(),
    ) {
        items(state.items) { item ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(4.dp),
            ) {
                HgImageSelector(
                    imageUrl = item.imageUrl,
                    contentDescription = item.name,
                    selected = item.isSelected,
                    onClick = { state.onToggleGoods(item) },
                    modifier = Modifier.size(168.dp),
                    cnt = item.order,
                )
                HeightSpacer(4)
                HgText(
                    text = item.name,
                    style = HGTypography.label1Medium,
                    maxLines = 2,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        }
    }
}
