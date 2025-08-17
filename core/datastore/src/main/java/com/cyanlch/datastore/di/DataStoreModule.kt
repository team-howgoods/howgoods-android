package com.cyanlch.datastore.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.cyanlch.data.datasource.auth.UserTokenDataStore
import com.cyanlch.datastore.UserTokenDataStoreImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataStoreModule {

    @Binds
    @Singleton
    abstract fun bindUserTokenDataStore(
        userTokenDataStoreImpl: UserTokenDataStoreImpl,
    ): UserTokenDataStore

    companion object {
        @Provides
        @Singleton
        fun provideDataStore(@ApplicationContext context: Context): DataStore<Preferences> {
            return PreferenceDataStoreFactory.create(
                corruptionHandler = ReplaceFileCorruptionHandler {
                    it.printStackTrace()
                    emptyPreferences()
                },
                migrations = emptyList(),
                produceFile = { context.preferencesDataStoreFile("user_preferences") },
                scope = CoroutineScope(Dispatchers.IO + SupervisorJob()),
            )
        }
    }
}
