package com.cyanlch.login.social.di

import com.cyanlch.login.social.SocialLogin
import com.cyanlch.login.social.SocialPlatform
import com.cyanlch.login.social.kakao.KakaoLoginHelper
import com.cyanlch.login.social.naver.NaverLoginHelper
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module
@InstallIn(SingletonComponent::class)
interface SocialLoginModule {
    @Binds
    @IntoMap
    @ClassKey(SocialPlatform.Kakao::class)
    fun bindKakao(impl: KakaoLoginHelper): SocialLogin

    @Binds
    @IntoMap
    @ClassKey(SocialPlatform.Naver::class)
    fun bindNaver(impl: NaverLoginHelper): SocialLogin
}
