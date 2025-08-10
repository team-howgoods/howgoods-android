package com.cyanlch.data.datasource.auth

import com.cyanlch.domain.model.auth.KakaoLoginRequest
import com.cyanlch.domain.model.auth.NaverLoginRequest
import com.cyanlch.domain.model.auth.UserToken

interface AuthDataSource {
    suspend fun loginNaver(naverLoginRequest: NaverLoginRequest): UserToken
    suspend fun loginKakao(kakaoLoginRequest: KakaoLoginRequest): UserToken
}