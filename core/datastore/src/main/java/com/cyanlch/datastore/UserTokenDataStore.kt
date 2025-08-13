package com.cyanlch.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.cyanlch.data.datasource.auth.UserTokenDataStore
import com.cyanlch.domain.model.auth.UserToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.serialization.json.Json
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserTokenDataStoreImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : UserTokenDataStore {
    private val userTokenKey = stringPreferencesKey("user_token")

    override val userTokenFlow: Flow<UserToken?> = dataStore.data
        .map { preferences ->
            preferences[userTokenKey]
        }.distinctUntilChanged()
        .map { token ->
            token?.let { Json.decodeFromString<UserToken>(it) }
        }

    override suspend fun saveUserToken(userToken: UserToken) {
        dataStore.edit { preferences ->
            preferences[userTokenKey] = Json.encodeToString(userToken)
        }
    }

    override suspend fun clearUserToken() {
        dataStore.edit { preferences ->
            preferences.remove(userTokenKey)
        }
    }
}