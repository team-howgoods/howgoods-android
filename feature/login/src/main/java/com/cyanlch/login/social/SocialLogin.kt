package com.cyanlch.login.social

import android.content.Context

enum class SocialPlatform {
    KAKAO, NAVER
}

interface SocialLogin {
    suspend fun login(context: Context): Result<String>
}