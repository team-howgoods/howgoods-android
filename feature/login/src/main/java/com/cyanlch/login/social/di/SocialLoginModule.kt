package com.cyanlch.login.social.di

import com.cyanlch.domain.model.auth.SocialPlatform
import com.cyanlch.login.social.SocialLogin
import com.cyanlch.login.social.kakao.KakaoLoginHelper
import com.cyanlch.login.social.naver.NaverLoginHelper
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoMap

@MapKey
annotation class PlatformKey(val value: SocialPlatform)

@Module
@InstallIn(SingletonComponent::class)
interface SocialLoginModule {
    @Binds
    @IntoMap
    @PlatformKey(SocialPlatform.KAKAO)
    fun bindKakao(impl: KakaoLoginHelper): SocialLogin

    @Binds
    @IntoMap
    @PlatformKey(SocialPlatform.NAVER)
    fun bindNaver(impl: NaverLoginHelper): SocialLogin
}
