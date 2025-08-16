package com.cyanlch.main.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class TabState(
    @field:StringRes val labelResId: Int,
    @field:DrawableRes val iconResId: Int,
    val selected: Boolean
)