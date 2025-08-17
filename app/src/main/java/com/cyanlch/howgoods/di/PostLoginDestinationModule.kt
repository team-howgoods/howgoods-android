package com.cyanlch.howgoods.di

import com.cyanlch.login.postLogin.PostLoginDestination
import com.cyanlch.main.MainShellScreen
import com.slack.circuit.runtime.screen.Screen
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import javax.inject.Inject

class MainPostLoginDestination @Inject constructor() : PostLoginDestination {
    override fun screen(): Screen = MainShellScreen
}

@Module
@InstallIn(ActivityRetainedComponent::class)
interface PostLoginDestinationModule {
    @Binds
    fun providePostLoginDestination(dest: MainPostLoginDestination): PostLoginDestination
}
