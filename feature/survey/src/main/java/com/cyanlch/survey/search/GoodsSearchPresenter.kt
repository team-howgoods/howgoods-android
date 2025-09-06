package com.cyanlch.survey.search

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.cyanlch.domain.usecase.survey.SearchGoodsUseCase
import com.cyanlch.survey.model.SelectedGoods
import com.cyanlch.survey.model.SurveyStore
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.components.ActivityRetainedComponent
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch

class GoodsSearchPresenter @AssistedInject constructor(
    private val searchGoods: SearchGoodsUseCase,
    private val store: SurveyStore,
    @Assisted private val navigator: Navigator,
) : Presenter<GoodsSearchScreen.State> {
    @OptIn(FlowPreview::class)
    @Composable
    override fun present(): GoodsSearchScreen.State {
        val storeState by store.uiState.collectAsStateWithLifecycle()
        var query by remember { mutableStateOf("") }
        var items by remember { mutableStateOf(emptyList<GoodsSearchItem>()) }
        var cursor by remember { mutableStateOf<Int?>(null) }
        var isLoading by remember { mutableStateOf(false) }
        var errorMessage by remember { mutableStateOf<String?>(null) }
        val scope = rememberCoroutineScope()

        LaunchedEffect(Unit) {
            snapshotFlow { query }
                .debounce(500)
                .collectLatest { q ->
                    isLoading = true
                    cursor = null
                    searchGoods(q, null)
                        .onSuccess { result ->
                            val selected = storeState.form.selectedGoods
                            val orderMap = selected.mapIndexed { index, g -> g.id to index + 1 }.toMap()
                            val selectedIds = selected.map { it.id }
                            items = result.items.map {
                                GoodsSearchItem(
                                    id = it.id,
                                    name = it.name,
                                    imageUrl = it.imageUrl,
                                    isSelected = it.id in selectedIds,
                                    order = orderMap[it.id],
                                )
                            }
                            cursor = result.nextCursor
                        }
                        .onFailure { errorMessage = it.message }
                    isLoading = false
                }
        }

        fun loadMore() {
            val cur = cursor ?: return
            if (isLoading) return
            scope.launch {
                isLoading = true
                searchGoods(query, cur)
                    .onSuccess { result ->
                        val selected = storeState.form.selectedGoods
                        val orderMap = selected.mapIndexed { index, g -> g.id to index + 1 }.toMap()
                        val selectedIds = selected.map { it.id }
                        val more = result.items.map {
                            GoodsSearchItem(
                                id = it.id,
                                name = it.name,
                                imageUrl = it.imageUrl,
                                isSelected = it.id in selectedIds,
                                order = orderMap[it.id],
                            )
                        }
                        items = items + more
                        cursor = result.nextCursor
                    }
                    .onFailure { errorMessage = it.message }
                isLoading = false
            }
        }

        fun onToggle(item: GoodsSearchItem) {
            store.selectOrDeselectGoods(
                SelectedGoods(item.id, item.name, item.imageUrl),
            )
            val selected = store.uiState.value.form.selectedGoods
            val orderMap = selected.mapIndexed { index, g -> g.id to index + 1 }.toMap()
            val selectedIds = selected.map { it.id }
            items = items.map { g ->
                g.copy(
                    isSelected = g.id in selectedIds,
                    order = orderMap[g.id],
                )
            }
        }

        fun onBack() {
            navigator.pop()
        }

        return GoodsSearchScreen.State(
            query = query,
            items = items,
            isLoading = isLoading,
            canLoadMore = cursor != null,
            errorMessage = errorMessage,
            onQueryChange = { query = it },
            onToggleGoods = ::onToggle,
            onLoadMore = ::loadMore,
            onBack = ::onBack,
            onErrorShown = { errorMessage = null },
        )
    }

    @CircuitInject(GoodsSearchScreen::class, ActivityRetainedComponent::class)
    @AssistedFactory
    interface Factory {
        fun create(navigator: Navigator): GoodsSearchPresenter
    }
}
