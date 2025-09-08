package com.cyanlch.survey.di

import com.cyanlch.navigation.MainTabs
import com.cyanlch.navigation.TabSpec
import com.cyanlch.survey.R
import com.cyanlch.survey.anime.AnimeScreen
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(ActivityRetainedComponent::class)
object SurveyTab {
    @Provides @IntoSet @MainTabs
    fun provideSurveyTabs(): TabSpec = TabSpec(
        root = AnimeScreen,
        labelResId = R.string.tab_survey,
        iconResId = com.cyanlch.ui.R.drawable.ic_apple,
        order = 0,
    )
}
