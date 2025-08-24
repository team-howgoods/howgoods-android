package com.cyanlch.data.repository.di

import com.cyanlch.data.repository.impl.AuthRepositoryImpl
import com.cyanlch.data.repository.impl.SurveyRepositoryImpl
import com.cyanlch.domain.repository.AuthRepository
import com.cyanlch.domain.repository.SurveyRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Singleton
    @Binds
    fun bindAuthRepository(impl: AuthRepositoryImpl): AuthRepository
    @Singleton
    @Binds
    fun bindSurveyRepository(impl: SurveyRepositoryImpl): SurveyRepository
}
