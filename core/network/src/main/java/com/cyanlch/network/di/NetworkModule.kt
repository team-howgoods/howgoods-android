package com.cyanlch.network.di

import com.cyanlch.data.datasource.auth.AuthDataSource
import com.cyanlch.network.datasource.AuthDataSourceImpl
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
}