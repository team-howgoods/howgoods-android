package com.cyanlch.network.datasource

import com.cyanlch.data.datasource.auth.AuthDataSource
import com.cyanlch.domain.model.auth.KakaoLoginRequest
import com.cyanlch.domain.model.auth.NaverLoginRequest
import com.cyanlch.domain.model.auth.UserToken
import com.cyanlch.network.Endpoints.KAKAO_LOGIN
import com.cyanlch.network.Endpoints.NAVER_LOGIN
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
    override suspend fun loginNaver(naverLoginRequest: NaverLoginRequest): UserToken {
        return ktorClient.post {
            url {
                path(NAVER_LOGIN)
            }
            setBody(naverLoginRequest)
        }.unwrap<UserToken>()
    }

    override suspend fun loginKakao(kakaoLoginRequest: KakaoLoginRequest): UserToken {
        return ktorClient.post {
            url {
                path(KAKAO_LOGIN)
            }
            setBody(kakaoLoginRequest)
        }.unwrap<UserToken>()
    }
}