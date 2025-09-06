package com.cyanlch.network.ext

import com.cyanlch.data.datasource.auth.UserTokenDataStore
import com.cyanlch.domain.model.auth.UserToken
import com.cyanlch.network.Endpoints
import com.cyanlch.network.RefreshClient
import com.cyanlch.network.model.RefreshTokenRequest
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TokenRefresher @Inject constructor(
    @param:RefreshClient private val refreshClient: HttpClient,
    private val userTokenDataStore: UserTokenDataStore,
) {
    private val mutex = Mutex()

    suspend fun refresh(oldRefreshToken: String?): BearerTokens? = mutex.withLock {
        val token = oldRefreshToken?.takeIf { it.isNotBlank() }
            ?: run {
                userTokenDataStore.clearUserToken()
                return null
            }

        return try {
            val res = refreshClient.post(Endpoints.REFRESH_TOKEN) {
                setBody(RefreshTokenRequest(token))
            }.body<UserToken>()

            userTokenDataStore.saveUserToken(res)
            BearerTokens(res.accessToken, res.refreshToken)
        } catch (e: ClientRequestException) { // 4xx
            if (e.response.status == HttpStatusCode.Unauthorized ||
                e.response.status == HttpStatusCode.Forbidden
            ) {
                userTokenDataStore.clearUserToken()
            }
            null
        } catch (_: ServerResponseException) { // 5xx
            null
        } catch (_: Exception) {
            null
        }
    }
}
