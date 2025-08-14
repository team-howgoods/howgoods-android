package com.cyanlch.domain.usecase.auth

import com.cyanlch.domain.model.auth.UserToken
import com.cyanlch.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserTokenFlowUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    operator fun invoke(): Flow<UserToken?> {
        return authRepository.getUserTokenFlow()
    }
}