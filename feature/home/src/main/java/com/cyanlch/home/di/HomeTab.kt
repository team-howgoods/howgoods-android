package com.cyanlch.home.di

import com.cyanlch.home.HomeScreen
import com.cyanlch.home.R
import com.cyanlch.navigation.MainTabs
import com.cyanlch.navigation.TabSpec
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(ActivityRetainedComponent::class)
object HomeTab {
    @Provides @IntoSet @MainTabs
    fun provideMainTabs(): TabSpec = TabSpec(
        root = HomeScreen,
        labelResId = R.string.tab_home,
        iconResId = com.cyanlch.ui.R.drawable.ic_apple,
        order = 0,
    )
}
