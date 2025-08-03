package com.cyanlch.login.social

import android.content.Context
import javax.inject.Inject
import kotlin.reflect.KClass

class SocialLoginDispatcher @Inject constructor(
    private val loginMap: Map<
        SocialPlatform,
        @JvmSuppressWildcards SocialLogin
    >
) {
    fun login(platform: SocialPlatform, context: Context) {
        loginMap[platform]?.login(context)
            ?: error("Unsupported social platform: $platform")
    }
}