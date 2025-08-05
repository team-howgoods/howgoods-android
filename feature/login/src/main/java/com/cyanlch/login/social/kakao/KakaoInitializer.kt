package com.cyanlch.login.social.kakao

import android.content.Context
import com.cyanlch.login.BuildConfig
import androidx.startup.Initializer
import com.kakao.sdk.common.KakaoSdk

class KakaoInitializer : Initializer<Unit> {
    override fun create(context: Context) {
        KakaoSdk.init(
            context = context,
            appKey = BuildConfig.KAKAO_NATIVE_APP_KEY
        )
    }
    override fun dependencies(): List<Class<out Initializer<*>>> = emptyList()
}