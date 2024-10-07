package com.ricky.adocaoapp.data.network.api

import com.ricky.adocaoapp.domain.models.Token
import com.ricky.adocaoapp.utils.Constants
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface RefreshTokenAPI {
    @POST(Constants.USER_REFRESH_TOKEN_ENDPOINT)
    suspend fun refreshToken(@Body token: Token): Response<Token>
}