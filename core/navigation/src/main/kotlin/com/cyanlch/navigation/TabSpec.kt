package com.cyanlch.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.slack.circuit.runtime.screen.Screen
import javax.inject.Qualifier

data class TabSpec(
    val root: Screen,
    @field:StringRes val labelResId: Int,
    @field:DrawableRes val iconResId: Int,
    val order: Int = 0,
)

@Qualifier annotation class MainTabs
