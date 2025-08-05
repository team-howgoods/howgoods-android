package com.cyanlch.login

import android.util.Log
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.kakao.sdk.common.KakaoSdk
import com.kakao.sdk.common.util.Utility
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class KakaoKeyHashTest {

    @Test
    fun getKakaoKeyHash() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        KakaoSdk.init(context, BuildConfig.KAKAO_NATIVE_APP_KEY)
        val keyHash = Utility.getKeyHash(context)
        Log.d("KeyHash", "카카오 디버그 키 해시: $keyHash")
    }
}