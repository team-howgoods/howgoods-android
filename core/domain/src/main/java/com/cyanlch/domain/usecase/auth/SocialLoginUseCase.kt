package com.cyanlch.domain.usecase.auth

import com.cyanlch.domain.model.auth.SocialLoginRequest
import com.cyanlch.domain.model.auth.UserToken
import com.cyanlch.domain.repository.AuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SocialLoginUseCase @Inject constructor(
    private val authRepository: AuthRepository,
) {
    suspend operator fun invoke(
        socialLoginRequest: SocialLoginRequest,
    ): Result<UserToken> {
        return withContext(Dispatchers.IO) {
            runCatching {
                val userToken = authRepository.loginSocial(socialLoginRequest)
                authRepository.saveUserToken(userToken)
                userToken
            }
        }
    }
}
