package com.cyanlch.domain.model.auth

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserToken(
    val accessToken: String,
    val refreshToken: String,
    @SerialName("memberEmail")
    val email: String
)