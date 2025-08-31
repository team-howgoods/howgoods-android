package com.cyanlch.main.di

import com.cyanlch.main.MainShellScreen
import com.cyanlch.navigation.MainShellStarter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
object MainShellStarterModule {
    @Provides
    fun provideMainShellStarter(): MainShellStarter = MainShellStarter { MainShellScreen }
}
