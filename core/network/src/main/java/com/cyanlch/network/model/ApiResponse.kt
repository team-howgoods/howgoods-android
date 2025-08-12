package com.cyanlch.network.model

import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse<T>(
    val code: Int,
    val message: String,
    val validationErrors: String? = null,
    val data: T? = null
)

class ApiException(
    val code: Int,
    override val message: String,
    val validationErrors: String? = null
) : RuntimeException(message)


suspend inline fun <reified T> HttpResponse.unwrap(): T {
    val env = body<ApiResponse<T>>()
    val data = env.data
    if (env.code == 200 && data != null) return data
    throw ApiException(env.code, env.message, env.validationErrors)
}
