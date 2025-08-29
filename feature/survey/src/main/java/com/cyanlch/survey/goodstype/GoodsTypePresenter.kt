package com.cyanlch.survey.goodstype

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.cyanlch.survey.model.SurveyStore
import com.cyanlch.survey.selection.GoodsSelectionScreen
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.components.ActivityRetainedComponent

class GoodsTypePresenter @AssistedInject constructor(
    private val store: SurveyStore,
    @Assisted private val navigator: Navigator,
) : Presenter<GoodsTypeScreen.State> {
    @Composable
    override fun present(): GoodsTypeScreen.State {
        val storeState by store.uiState.collectAsStateWithLifecycle()

        LaunchedEffect(Unit) {
            store.loadGoodsTypeIfEmpty()
        }

        val goodsTypes = remember(
            storeState.form.goodsTypes,
            storeState.form.selectedGoodsTypes,
        ) {
            storeState.form.goodsTypes.map { goodsType ->
                GoodsTypeGridItem(
                    goodsTypeId = goodsType.goodsTypeId,
                    name = goodsType.name,
                    imageUrl = goodsType.imageUrl,
                    isSelected = goodsType.goodsTypeId in storeState.form.selectedGoodsTypes,
                )
            }
        }

        fun onNext() {
            navigator.goTo(GoodsSelectionScreen)
        }

        fun onBack() {
            navigator.pop()
        }

        return GoodsTypeScreen.State(
            goodsTypes = goodsTypes,
            selectedGoodsTypes = storeState.form.selectedGoodsTypes,
            selectedGoodsTypeCount = storeState.form.selectedGoodsTypes.size,
            onToggleGoodsType = store::selectOrDeselectGoodsType,
            onToggleAllGoodsType = store::selectOrDeselectAllGoodsType,
            onNext = ::onNext,
            onBack = ::onBack,
        )
    }

    @CircuitInject(GoodsTypeScreen::class, ActivityRetainedComponent::class)
    @AssistedFactory
    interface Factory {
        fun create(navigator: Navigator): GoodsTypePresenter
    }
}
