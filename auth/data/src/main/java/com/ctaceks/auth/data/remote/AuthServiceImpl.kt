package com.ctaceks.auth.data.remote

import android.util.Log
import com.ctaceks.auth.data.remote.model.LoginRequestDto
import com.ctaceks.auth.data.remote.model.RefreshTokenDto
import com.ctaceks.auth.data.remote.model.TokenPairDto
import com.ctaceks.core.data.RequestError
import com.ctaceks.core.data.RequestResult
import com.ctaceks.core.data.result
import com.ctaceks.core.data.safeRequest
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import java.util.UUID
import javax.inject.Inject

const val BASE_URL = "http://158.160.91.75:8081"
const val LIST = "/api/v1"
const val AUTH = "/auth"
const val USERNAME = "/username"
const val LOGIN = "/login"
const val REGISTER = "/register"

class AuthServiceImpl @Inject constructor(
    private val client: HttpClient
) : AuthService {

    override suspend fun authLoginPost(user: LoginRequestDto): TokenPairDto {
        val response: RequestResult<TokenPairDto> = client.safeRequest {
            post(BASE_URL + LIST + AUTH + USERNAME + LOGIN) {
                setBody(user)
                header("Client-Id", UUID.randomUUID().toString())
            }
        }
        return when (response) {
            is RequestResult.Success -> response.value
            is RequestResult.Error -> {
                Log.e("auth_service_error", "${response.e.code} ${response.e.name}")
                TokenPairDto(
                    "", refreshToken = RefreshTokenDto(
                        "${response.e.code} ${response.e.name}", 0
                    )
                )
            }
        }
    }

    override suspend fun authRegisterPost(user: LoginRequestDto): TokenPairDto {
        val response: RequestResult<TokenPairDto> = client.safeRequest {
            post(BASE_URL + LIST + AUTH + USERNAME + REGISTER) {
                setBody(user)
                header("Client-Id", UUID.randomUUID().toString())
            }
        }
        return when (response) {
            is RequestResult.Success -> response.value
            is RequestResult.Error -> {
                Log.e("auth_service_error", "${response.e.code} ${response.e.name}")
                TokenPairDto(
                    "", refreshToken = RefreshTokenDto(
                        "${response.e.code} ${response.e.name}", 0
                    )
                )
            }
        }
    }
}

suspend inline fun <reified T> HttpClient.safeRequest(
    request: HttpClient.() -> HttpResponse
): RequestResult<T> {
    return try {
        request().result<T>()
    } catch (e: Exception) {
        Log.e("safeRequest", e.toString())
        RequestResult.Error(RequestError.NO_CONNECTION)
    }
}