package com.ricky.adocaoapp.data.repository

import com.ricky.adocaoapp.data.network.api.RefreshTokenAPI
import com.ricky.adocaoapp.domain.models.Token
import com.ricky.adocaoapp.domain.repository.TokenRepository
import retrofit2.Response
import javax.inject.Inject

class TokenRepositoryImpl @Inject constructor(private val refreshTokenAPI: RefreshTokenAPI) :
    TokenRepository {
    override suspend fun refreshToken(token: Token): Response<Token> =
        refreshTokenAPI.refreshToken(token)

}