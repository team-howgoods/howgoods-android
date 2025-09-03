package com.cyanlch.domain.model.auth

import kotlinx.serialization.Serializable

@Serializable
data class UserToken(
    val accessToken: String,
    val refreshToken: String,
    val nickname: String,
)
