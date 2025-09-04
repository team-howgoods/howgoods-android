package com.cyanlch.network.di

import com.cyanlch.data.datasource.auth.AuthDataSource
import com.cyanlch.data.datasource.survey.SurveyDataSource
import com.cyanlch.network.datasource.AuthDataSourceImpl
import com.cyanlch.network.datasource.SurveyDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface NetworkModule {
    @Singleton
    @Binds
    fun bindAuthDataSource(impl: AuthDataSourceImpl): AuthDataSource

    @Singleton
    @Binds
    fun bindSurveyDataSource(impl: SurveyDataSourceImpl): SurveyDataSource
}
