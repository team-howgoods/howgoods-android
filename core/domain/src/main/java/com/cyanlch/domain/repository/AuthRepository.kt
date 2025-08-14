package com.cyanlch.domain.repository

import com.cyanlch.domain.model.auth.SocialLoginRequest
import com.cyanlch.domain.model.auth.UserToken
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun loginSocial(socialLoginRequest: SocialLoginRequest): UserToken
    suspend fun saveUserToken(userToken: UserToken)
    suspend fun clearUserToken()
    fun getUserTokenFlow(): Flow<UserToken?>
}