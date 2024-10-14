package com.ricky.adocaoapp.domain.repository

import com.ricky.adocaoapp.domain.models.Token
import retrofit2.Response
import retrofit2.http.Body

interface TokenRepository {
    suspend fun refreshToken(@Body token: Token): Response<Token>

}