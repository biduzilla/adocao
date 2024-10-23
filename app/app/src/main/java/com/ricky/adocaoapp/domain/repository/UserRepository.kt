package com.ricky.adocaoapp.domain.repository

import com.ricky.adocaoapp.domain.models.Login
import com.ricky.adocaoapp.domain.models.ResetSenha
import com.ricky.adocaoapp.domain.models.Token
import com.ricky.adocaoapp.domain.models.Usuario
import com.ricky.adocaoapp.domain.models.VerificarCod
import retrofit2.Response
import retrofit2.http.Path

interface UserRepository {
    suspend fun login(login: Login): Response<Token>
    suspend fun save(usuario: Usuario): Response<Usuario>
    suspend fun resetPassword(email: String): Response<Void>
    suspend fun verifyCod(verificarCod: VerificarCod): Response<Void>
    suspend fun changePassword(resetSenha: ResetSenha): Response<Void>
    suspend fun getById( idUser:String): Response<Usuario>
    suspend fun getUsuariosBySenderId(
        idUser:String
    ): Response<List<Usuario>>
}