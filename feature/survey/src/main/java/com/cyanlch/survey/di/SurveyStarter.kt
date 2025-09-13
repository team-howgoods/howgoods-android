package com.cyanlch.survey.di

import com.cyanlch.navigation.screenstarter.SurveyStarter
import com.cyanlch.survey.anime.AnimeScreen
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
object SurveyStarterModule {
    @Provides
    fun provideSurveyStarter(): SurveyStarter = SurveyStarter { AnimeScreen }
}
