package com.cyanlch.network.datasource

import com.cyanlch.data.datasource.auth.AuthDataSource
import com.cyanlch.domain.model.auth.SocialLoginRequest
import com.cyanlch.domain.model.auth.SocialPlatform
import com.cyanlch.domain.model.auth.UserToken
import com.cyanlch.network.Endpoints
import com.cyanlch.network.model.unwrap
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.path
import javax.inject.Inject

class AuthDataSourceImpl @Inject constructor(
    private val ktorClient: HttpClient
) : AuthDataSource{
    override suspend fun loginSocial(
        socialLoginRequest: SocialLoginRequest
    ): UserToken {
        val path = when (socialLoginRequest.platform) {
            SocialPlatform.KAKAO -> Endpoints.KAKAO_LOGIN
            SocialPlatform.NAVER -> Endpoints.NAVER_LOGIN
        }

        return ktorClient.post {
            url {
                path(path)
            }
            setBody(socialLoginRequest)
        }.unwrap<UserToken>()
    }
}