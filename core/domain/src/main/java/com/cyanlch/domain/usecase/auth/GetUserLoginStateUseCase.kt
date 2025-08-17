package com.cyanlch.domain.usecase.auth

import com.cyanlch.domain.model.auth.UserToken
import com.cyanlch.domain.repository.AuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.withContext
import javax.inject.Inject

sealed interface LoginState {
    data object UnAuthorized : LoginState
    data object NotLogin : LoginState
    data class Login(val userToken: UserToken) : LoginState
}

class GetUserLoginStateUseCase @Inject constructor(
    private val authRepository: AuthRepository,
) {
    suspend operator fun invoke(): LoginState {
        return withContext(Dispatchers.IO) {
            val userInfo = authRepository.getUserTokenFlow().firstOrNull()
            if (userInfo == null) {
                LoginState.NotLogin
            } else {
                LoginState.Login(userInfo)
            }
        }
    }
}
