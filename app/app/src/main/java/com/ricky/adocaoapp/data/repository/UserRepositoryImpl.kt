package com.ricky.adocaoapp.data.repository

import com.ricky.adocaoapp.data.network.api.RefreshTokenAPI
import com.ricky.adocaoapp.data.network.api.UserAPI
import com.ricky.adocaoapp.domain.models.Login
import com.ricky.adocaoapp.domain.models.ResetSenha
import com.ricky.adocaoapp.domain.models.Token
import com.ricky.adocaoapp.domain.models.Usuario
import com.ricky.adocaoapp.domain.models.VerificarCod
import com.ricky.adocaoapp.domain.repository.UserRepository
import retrofit2.Response
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val api: UserAPI,
) : UserRepository {
    override suspend fun login(login: Login): Response<Token> = api.login(login)

    override suspend fun save(usuario: Usuario): Response<Usuario> = api.save(usuario)

    override suspend fun resetPassword(email: String): Response<Void> = api.resetPassword(email)

    override suspend fun verifyCod(verificarCod: VerificarCod): Response<Void> =
        api.verifyCod(verificarCod)

    override suspend fun changePassword(resetSenha: ResetSenha): Response<Void> =
        api.changePassword(resetSenha)

    override suspend fun getById(idUser: String): Response<Usuario> = api.getById(idUser)


}