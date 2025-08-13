package com.cyanlch.data.datasource.usertoken

import com.cyanlch.domain.model.auth.UserToken
import kotlinx.coroutines.flow.Flow

interface UserTokenDataStore {
    val userTokenFlow: Flow<UserToken?>
    suspend fun saveUserToken(userToken: UserToken)
    suspend fun clearUserToken()
}