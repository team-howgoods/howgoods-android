package com.cyanlch.login.social

import android.content.Context
import com.cyanlch.domain.model.auth.SocialPlatform
import javax.inject.Inject

class SocialLoginDispatcher @Inject constructor(
    private val loginMap: Map<SocialPlatform, @JvmSuppressWildcards SocialLogin>
) {
    suspend fun login(platform: SocialPlatform, context: Context): Result<String> {
        return loginMap[platform]?.login(context)
            ?: Result.failure(
                exception = IllegalStateException("Unsupported social platform: $platform")
            )
    }
}