package com.cyanlch.domain.usecase.auth

import com.cyanlch.domain.model.auth.UserToken
import com.cyanlch.domain.repository.AuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SaveUserTokenUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(userToken: UserToken) {
        withContext(Dispatchers.IO) {
            authRepository.saveUserToken(userToken)
        }
    }
}