package com.cyanlch.login.di

import com.cyanlch.login.LoginScreen
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
class LoginScreenStarterModule {
    @Provides
    fun provideLoginScreenStarter(): LoginScreenStarter = LoginScreenStarter { LoginScreen }

}