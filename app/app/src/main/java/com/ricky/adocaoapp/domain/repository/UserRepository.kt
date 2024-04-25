package com.ricky.adocaoapp.domain.repository

import com.ricky.adocaoapp.domain.models.Login
import com.ricky.adocaoapp.domain.models.ResetSenha
import com.ricky.adocaoapp.domain.models.Token
import com.ricky.adocaoapp.domain.models.Usuario
import com.ricky.adocaoapp.domain.models.VerificarCod
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Path

interface UserRepository {
    suspend fun login(@Body login: Login): Response<Token>
    suspend fun save(@Body usuario: Usuario): Response<Usuario>
    suspend fun resetPassword(@Path("email") email: String): Response<Void>
    suspend fun verifyCod(@Body verificarCod: VerificarCod): Response<Void>
    suspend fun changePassword(@Body resetSenha: ResetSenha): Response<Void>
}