package com.cyanlch.data.repository.impl

import com.cyanlch.data.datasource.auth.AuthDataSource
import com.cyanlch.domain.model.auth.SocialLoginRequest
import com.cyanlch.domain.model.auth.UserToken
import com.cyanlch.domain.repository.AuthRepository
import javax.inject.Inject


class AuthRepositoryImpl @Inject constructor(
    private val authDataSource: AuthDataSource
): AuthRepository {
    override suspend fun loginSocial(socialLoginRequest: SocialLoginRequest): UserToken {
        return authDataSource.loginSocial(socialLoginRequest)
    }
}