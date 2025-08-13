package com.cyanlch.domain.model.auth

import kotlinx.serialization.Serializable

enum class SocialPlatform {
    KAKAO, NAVER
}

@Serializable
data class SocialLoginRequest(
    val platform: SocialPlatform,
    val code: String
)
