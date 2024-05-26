package com.ctaceks.auth.data.remote

import com.ctaceks.auth.data.remote.model.RefreshTokenDto
import com.ctaceks.auth.data.remote.model.TokenPairDto
import com.ctaceks.auth.domain.model.LoginRequest
import com.ctaceks.core.data.RequestResult
import io.ktor.client.HttpClient
import javax.inject.Inject

class AuthServiceImpl @Inject constructor(
    private val client: HttpClient
) : AuthService {

    override suspend fun authLoginPost(user: LoginRequest): TokenPairDto =
//        client.safeRequest { post { requestHeaders(); setBody(LoginRequest) }  }
        RequestResult.Success(
            TokenPairDto(
                "access", refreshToken = RefreshTokenDto(
                    "refresh", 112321
                )
            )
        ).value

    override suspend fun authRegisterPost(user: LoginRequest): TokenPairDto =
        RequestResult.Success(
            TokenPairDto(
                "access", refreshToken = RefreshTokenDto(
                    "refresh", 112321
                )
            )
        ).value


    /*    private fun HttpMessageBuilder.requestHeaders() {
            headers {
                append(TOKEN, "Token")
            }
        }*/
}
