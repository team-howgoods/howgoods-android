package com.cyanlch.login.social.kakao

import android.content.Context
import android.util.Log
import com.cyanlch.login.social.SocialLogin
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume

class KakaoLoginHelper @Inject constructor(): SocialLogin {
    companion object {
        private const val TAG = "KakaoLoginHelper"
    }

    override suspend fun login(context: Context): Result<String> {
        return suspendCancellableCoroutine { continuation ->
            val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
                if (error != null) {
                    Log.e(TAG, "카카오계정으로 로그인 실패", error)
                    if (continuation.isActive) {
                        continuation.resume(Result.failure(error))
                    }
                } else if (token != null) {
                    Log.i(TAG, "카카오계정으로 로그인 성공 ${token.accessToken}")
                    continuation.resume(Result.success(token.accessToken))
                    if (continuation.isActive) {
                        continuation.resume(Result.success(token.accessToken))
                    }
                }
            }

            if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
                UserApiClient.instance.loginWithKakaoTalk(context) { token, error ->
                    if (error != null) {
                        if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                            if (continuation.isActive) {
                                continuation.resume(Result.failure(error))
                            }
                        }
                        UserApiClient.instance.loginWithKakaoAccount(context, callback = callback)
                    } else if (token != null) {
                        Log.i(TAG, "카카오톡으로 로그인 성공 ${token.accessToken}")
                        if (continuation.isActive) {
                            continuation.resume(Result.success(token.accessToken))
                        }
                    }
                }
            } else {
                UserApiClient.instance.loginWithKakaoAccount(context, callback = callback)
            }
        }
    }

    override fun logout() {
        UserApiClient.instance.logout { error ->
            if (error != null) {
                Log.e(TAG, "카카오계정 로그아웃 실패", error)
            } else {
                Log.i(TAG, "카카오계정 로그아웃 성공")
            }
        }
    }
}