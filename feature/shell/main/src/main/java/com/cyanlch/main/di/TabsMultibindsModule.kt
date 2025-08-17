package com.cyanlch.main.di

import com.cyanlch.navigation.MainTabs
import com.cyanlch.navigation.TabSpec
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.multibindings.Multibinds

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class TabsMultibindsModule {
    @Multibinds
    @MainTabs
    abstract fun mainTabs(): Set<TabSpec>
}
