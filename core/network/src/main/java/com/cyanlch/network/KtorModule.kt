package com.cyanlch.network

import android.util.Log
import com.cyanlch.data.datasource.auth.UserTokenDataStore
import com.cyanlch.domain.model.auth.UserToken
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.engine.cio.endpoint
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.accept
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.URLProtocol
import io.ktor.http.contentType
import io.ktor.http.headers
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object KtorModule {
    @Provides
    @Singleton
    fun provideKtorClient(
        userTokenDataStore: UserTokenDataStore,
    ) = HttpClient(CIO) {
        install(Logging) {
            level = if (BuildConfig.DEBUG) LogLevel.ALL else LogLevel.NONE
            val maxMessageSize = 4000
            logger = object : Logger {
                override fun log(message: String) {
                    message.chunked(maxMessageSize).forEach {
                        Log.i("KtorClient", it)
                    }
                }
            }
        }

        install(ContentNegotiation) {
            json(
                Json {
                    prettyPrint = true
                    encodeDefaults = true
                    isLenient = true
                    explicitNulls = false
                    ignoreUnknownKeys = true
                },
            )
        }

        install(Auth) {
            bearer {
                loadTokens {
                    userTokenDataStore.userTokenFlow.firstOrNull()?.let { token ->
                        BearerTokens(
                            accessToken = token.accessToken,
                            refreshToken = token.refreshToken,
                        )
                    }
                }

                refreshTokens {
                    val res = client.post(
                        "${BuildConfig.API_BASE_URL}/${Endpoints.REFRESH_TOKEN}",
                    ) {
                        contentType(ContentType.Application.Json)
                        setBody(mapOf("refreshToken" to (oldTokens?.refreshToken ?: "")))
                        markAsRefreshTokenRequest()
                    }.body<UserToken>()
                    userTokenDataStore.saveUserToken(res)

                    BearerTokens(
                        accessToken = res.accessToken,
                        refreshToken = res.refreshToken,
                    )
                }
            }
        }

        engine {
            requestTimeout = 15_000

            endpoint {
                connectTimeout = 60_000
                connectAttempts = 1
                keepAliveTime = 5_000
                maxConnectionsPerRoute = 100
                pipelineMaxSize = 20
            }
        }

        defaultRequest {
            url {
                protocol = URLProtocol.HTTPS
                host = BuildConfig.API_BASE_URL
            }
            headers {
                accept(ContentType.Application.Json)
                contentType(ContentType.Application.Json)
            }
        }
    }
}
