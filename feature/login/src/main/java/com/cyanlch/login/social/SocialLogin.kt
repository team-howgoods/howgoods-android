package com.cyanlch.login.social

import android.content.Context

sealed class SocialPlatform() {
    object Kakao : SocialPlatform()
    object Naver : SocialPlatform()
}
interface SocialLogin {
    fun login(context: Context)
}