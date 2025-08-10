package com.cyanlch.network.model

import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

val jsonParser =Json {
    ignoreUnknownKeys = true
    isLenient = true
    explicitNulls = false
    encodeDefaults = true
}

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
/*

suspend fun <T> HttpResponse.unwrap(
    deserializer: DeserializationStrategy<T>,
    json: Json = jsonParser
): T {
    val env: ApiEnvelope = body()
    if (env.code == 200) {
        val elem = env.data ?: throw ApiException(500, "Empty data on success", env.validationErrors)
        return json.decodeFromJsonElement(deserializer, elem) // opt-in 불필요
    }
    throw ApiException(env.code, env.message, env.validationErrors)
}
*/
