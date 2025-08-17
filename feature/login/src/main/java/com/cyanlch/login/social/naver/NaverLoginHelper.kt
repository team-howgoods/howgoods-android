package com.cyanlch.login.social.naver

import android.content.Context
import android.util.Log
import com.cyanlch.login.social.SocialLogin
import com.navercorp.nid.NaverIdLoginSDK
import com.navercorp.nid.oauth.OAuthLoginCallback
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume

class NaverLoginHelper @Inject constructor() : SocialLogin {
    companion object {
        const val TAG = "NaverLoginHelper"
    }

    override suspend fun login(context: Context): Result<String> {
        return suspendCancellableCoroutine { continuation ->
            val oauthLoginCallback = object : OAuthLoginCallback {
                override fun onSuccess() {
                    if (!continuation.isActive) return
                    Log.e(
                        TAG,
                        "NaverIdLoginSDK.getAccessToken(): " +
                            "${NaverIdLoginSDK.getAccessToken()}",
                    )
                    val accessToken = NaverIdLoginSDK.getAccessToken()
                    if (!accessToken.isNullOrBlank()) {
                        if (continuation.isActive) {
                            continuation.resume(Result.success(accessToken))
                        }
                    } else {
                        if (continuation.isActive) {
                            continuation.resume(
                                value = Result.failure(
                                    exception = IllegalArgumentException(
                                        "accessToken is null",
                                    ),
                                ),
                            )
                        }
                    }
                }
                override fun onFailure(httpStatus: Int, message: String) {
                    val errorCode = NaverIdLoginSDK.getLastErrorCode().code
                    val errorDescription = NaverIdLoginSDK.getLastErrorDescription()
                    Log.e(TAG, "errorCode:$errorCode, errorDesc:$errorDescription")
                    if (continuation.isActive) {
                        continuation.resume(
                            value = Result.failure(
                                exception = IllegalArgumentException(
                                    NaverIdLoginSDK.getLastErrorDescription(),
                                ),
                            ),
                        )
                    }
                }
                override fun onError(errorCode: Int, message: String) {
                    onFailure(errorCode, message)
                }
            }

            NaverIdLoginSDK.authenticate(context, oauthLoginCallback)
        }
    }

    override fun logout() {
        NaverIdLoginSDK.logout()
    }
}
