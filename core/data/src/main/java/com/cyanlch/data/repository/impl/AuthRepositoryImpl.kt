package com.cyanlch.data.repository.impl

import com.cyanlch.data.datasource.auth.AuthDataSource
import com.cyanlch.data.datasource.auth.UserTokenDataStore
import com.cyanlch.domain.model.auth.SocialLoginRequest
import com.cyanlch.domain.model.auth.UserToken
import com.cyanlch.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authDataSource: AuthDataSource,
    private val userTokenDataStore: UserTokenDataStore,
) : AuthRepository {
    override suspend fun loginSocial(socialLoginRequest: SocialLoginRequest): UserToken {
        return authDataSource.loginSocial(socialLoginRequest)
    }

    override suspend fun saveUserToken(userToken: UserToken) {
        userTokenDataStore.saveUserToken(userToken)
    }

    override suspend fun clearUserToken() {
        userTokenDataStore.clearUserToken()
    }

    override fun getUserTokenFlow(): Flow<UserToken?> {
        return userTokenDataStore.userTokenFlow
    }
}
