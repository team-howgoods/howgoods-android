package com.cyanlch.login.social

import android.content.Context

enum class SocialPlatform {
    KAKAO, NAVER
}

interface SocialLogin {
    fun login(context: Context)
    fun logout()
}