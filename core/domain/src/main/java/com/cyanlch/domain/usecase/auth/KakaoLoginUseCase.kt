package com.cyanlch.domain.usecase.auth

import com.cyanlch.domain.model.auth.KakaoLoginRequest
import com.cyanlch.domain.model.auth.UserToken
import com.cyanlch.domain.repository.AuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class KakaoLoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(kakaoLoginRequest: KakaoLoginRequest): Result<UserToken> {
        return withContext(Dispatchers.IO) {
            runCatching {
                authRepository.loginKakao(kakaoLoginRequest)
            }
        }
    }
}