package com.cyanlch.data.repository.impl

import com.cyanlch.data.datasource.auth.AuthDataSource
import com.cyanlch.domain.model.auth.KakaoLoginRequest
import com.cyanlch.domain.model.auth.NaverLoginRequest
import com.cyanlch.domain.model.auth.UserToken
import com.cyanlch.domain.repository.AuthRepository
import javax.inject.Inject


class AuthRepositoryImpl @Inject constructor(
    private val authDataSource: AuthDataSource
): AuthRepository {
    override suspend fun loginNaver(naverLoginRequest: NaverLoginRequest): UserToken {
        return authDataSource.loginNaver(naverLoginRequest)
    }

    override suspend fun loginKakao(kakaoLoginRequest: KakaoLoginRequest): UserToken {
        return authDataSource.loginKakao(kakaoLoginRequest)
    }
}