package com.cyanlch.login.social.naver

import android.content.Context
import android.util.Log
import com.cyanlch.login.social.SocialLogin
import com.navercorp.nid.NaverIdLoginSDK
import com.navercorp.nid.oauth.OAuthLoginCallback
import javax.inject.Inject

class NaverLoginHelper @Inject constructor() : SocialLogin {
    companion object {
        const val TAG = "NaverLoginHelper"
    }

    override fun login(context: Context) {
        NaverIdLoginSDK.authenticate(context, oauthLoginCallback)
    }

    val oauthLoginCallback = object : OAuthLoginCallback {
        override fun onSuccess() {
            // 네이버 로그인 인증이 성공했을 때 수행할 코드 추가
            Log.e(TAG, "NaverIdLoginSDK.getAccessToken(): ${NaverIdLoginSDK.getAccessToken()}")
        }
        override fun onFailure(httpStatus: Int, message: String) {
            val errorCode = NaverIdLoginSDK.getLastErrorCode().code
            val errorDescription = NaverIdLoginSDK.getLastErrorDescription()
            Log.e(TAG, "errorCode:$errorCode, errorDesc:$errorDescription")
        }
        override fun onError(errorCode: Int, message: String) {
            onFailure(errorCode, message)
        }
    }

    override fun logout() {
        NaverIdLoginSDK.logout()
    }
}