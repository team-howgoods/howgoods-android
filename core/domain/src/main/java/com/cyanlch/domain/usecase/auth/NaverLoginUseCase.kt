package com.cyanlch.domain.usecase.auth

import com.cyanlch.domain.model.auth.NaverLoginRequest
import com.cyanlch.domain.repository.AuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NaverLoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(naverLoginRequest: NaverLoginRequest) {
        return withContext(Dispatchers.IO) {
            runCatching {
                authRepository.loginNaver(naverLoginRequest)
            }
        }
    }
}