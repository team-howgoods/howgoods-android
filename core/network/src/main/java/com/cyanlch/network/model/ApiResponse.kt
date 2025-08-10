package com.cyanlch.network.model

import io.ktor.client.call.body
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

val jsonParser =Json {
    ignoreUnknownKeys = true
    isLenient = true
    explicitNulls = false
    encodeDefaults = true
}

@InternalSerializationApi
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

@InternalSerializationApi
suspend inline fun <reified T> io.ktor.client.statement.HttpResponse.unwrap(): T {
    val env = body<ApiResponse<T>>()
    val data = env.data
    if (env.code == 200 && data != null) return data
    throw ApiException(env.code, env.message, env.validationErrors)
}
