package com.cyanlch.login.social

import android.content.Context
import javax.inject.Inject

class SocialLoginDispatcher @Inject constructor(
    private val loginMap: Map<@JvmSuppressWildcards Class<out SocialPlatform>,
            @JvmSuppressWildcards SocialLogin>
) {
    fun login(platform: SocialPlatform, context: Context) {
        loginMap[platform::class.java]?.login(context)
            ?: error("Unsupported social platform: $platform")
    }
}