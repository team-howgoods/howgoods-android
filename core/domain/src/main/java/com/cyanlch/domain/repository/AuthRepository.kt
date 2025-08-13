package com.cyanlch.domain.repository

import com.cyanlch.domain.model.auth.SocialLoginRequest
import com.cyanlch.domain.model.auth.UserToken

interface AuthRepository {
    suspend fun loginSocial(socialLoginRequest: SocialLoginRequest): UserToken
}