package com.cyanlch.data.datasource.auth

import com.cyanlch.domain.model.auth.SocialLoginRequest
import com.cyanlch.domain.model.auth.UserToken

interface AuthDataSource {
    suspend fun loginSocial(
        socialLoginRequest: SocialLoginRequest,
    ): UserToken
}
