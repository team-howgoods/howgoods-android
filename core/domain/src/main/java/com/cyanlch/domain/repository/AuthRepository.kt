package com.cyanlch.domain.repository

import com.cyanlch.domain.model.auth.KakaoLoginRequest
import com.cyanlch.domain.model.auth.NaverLoginRequest
import com.cyanlch.domain.model.auth.UserToken

interface AuthRepository {
    suspend fun loginNaver(naverLoginRequest: NaverLoginRequest): UserToken
    suspend fun loginKakao(kakaoLoginRequest: KakaoLoginRequest): UserToken
}