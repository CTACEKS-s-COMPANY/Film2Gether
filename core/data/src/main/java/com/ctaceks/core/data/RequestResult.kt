package com.ctaceks.core.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse

/**
 * [HttpResponse] wrapper
 */
sealed class RequestResult<out T> {
    data class Success<T>(val value: T): RequestResult<T>()
    data class Error<T>(val e: RequestError): RequestResult<T>()
}

/**
 * Custom request error representation
 */
enum class RequestError(val code: Int) {
    REVISION(400),
    AUTH(401),
    NOT_FOUND(404),
    SERVER(500),
    NO_CONNECTION(0),
    UNKNOWN(0),
    METHOD_IS_NOT_ALLOWED(405),
}

/**
 * Ktor HttpRequest wrapper for making safe requests
 */
suspend inline fun <reified T> HttpClient.safeRequest(
    request: HttpClient.() -> HttpResponse
): RequestResult<T> {
    return try {
        request().result<T>()
    } catch (e: Exception) {
        RequestResult.Error(RequestError.NO_CONNECTION)
    }
}

/**
 * Wraps [HttpResponse] to [RequestResult]
 */
suspend inline fun <reified T> HttpResponse.result(): RequestResult<T> {
    if (status.value in 200..299)
        return RequestResult.Success(body<T>())

    val error = when(status.value) {
        405 -> RequestError.METHOD_IS_NOT_ALLOWED
        400 -> RequestError.REVISION
        401 -> RequestError.AUTH
        404 -> RequestError.NOT_FOUND
        500 -> RequestError.SERVER
        else -> RequestError.UNKNOWN
    }
    return RequestResult.Error(error)
}
