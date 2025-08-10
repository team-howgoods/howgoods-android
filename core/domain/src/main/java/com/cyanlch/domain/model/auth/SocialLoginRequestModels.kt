package com.cyanlch.domain.model.auth

import kotlinx.serialization.Serializable

@Serializable
data class NaverLoginRequest(
    val code: String,
    val state: String
)

@Serializable
data class KakaoLoginRequest(
    val code: String
)