package com.cyanlch.network.model

import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse<T>(
    val code: Int,
    val message: String,
    val validationErrors: String? = null,
    val data: T? = null,
)

class ApiException(
    val code: Int,
    override val message: String,
    val validationErrors: String? = null,
) : RuntimeException(message)

suspend inline fun <reified T> HttpResponse.unwrap(): T {
    val env = body<ApiResponse<T>>()
    val data = env.data
    if (env.code in 200..299) {
        if (env.data != null) {
            return env.data
        } else if (Unit is T) {
            return Unit as T
        }
    }

    throw ApiException(env.code, env.message, env.validationErrors)
}
