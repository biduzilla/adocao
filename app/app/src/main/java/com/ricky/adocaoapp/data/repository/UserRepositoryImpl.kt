package com.ricky.adocaoapp.data.repository

import com.ricky.adocaoapp.data.network.api.UserAPI
import com.ricky.adocaoapp.domain.models.Login
import com.ricky.adocaoapp.domain.models.ResetSenha
import com.ricky.adocaoapp.domain.models.Token
import com.ricky.adocaoapp.domain.models.Usuario
import com.ricky.adocaoapp.domain.models.VerificarCod
import com.ricky.adocaoapp.domain.repository.UserRepository
import retrofit2.Response
import retrofit2.http.Path
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val api: UserAPI,
) : UserRepository {
    override suspend fun login(login: Login): Response<Token> = api.login(login)

    override suspend fun save(usuario: Usuario): Response<Usuario> = api.save(usuario)

    override suspend fun resetPassword(email: String): Response<Void> = api.resetPassword(email)

    override suspend fun verifyCod(
        cod: Int,
        email: String
    ): Response<Void> =
        api.verifyCod(
            cod = cod,
            email = email
        )

    override suspend fun changePassword(resetSenha: ResetSenha): Response<Void> =
        api.changePassword(resetSenha)

    override suspend fun getById(idUser: String): Response<Usuario> = api.getById(idUser)
    override suspend fun getUsuariosBySenderId(idUser: String): Response<List<Usuario>> =
        api.getUsuariosBySenderId(idUser)

    override suspend fun deleteUser(idUser: String): Response<Void> = api.deleteUser(idUser)
}