package com.ricky.adocaoapp.domain.use_case

import com.ricky.adocaoapp.domain.models.Login
import com.ricky.adocaoapp.domain.models.ResetSenha
import com.ricky.adocaoapp.domain.models.Token
import com.ricky.adocaoapp.domain.models.Usuario
import com.ricky.adocaoapp.domain.models.VerificarCod
import com.ricky.adocaoapp.domain.use_case.user.UseCaseChangePassword
import com.ricky.adocaoapp.domain.use_case.user.UseCaseLogin
import com.ricky.adocaoapp.domain.use_case.user.UseCaseResetPassword
import com.ricky.adocaoapp.domain.use_case.user.UseCaseSave
import com.ricky.adocaoapp.domain.use_case.user.UseCaseVerifyCod
import com.ricky.adocaoapp.domain.use_case.user.UseRefreshToken
import com.ricky.adocaoapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserManager @Inject constructor(
    private val changePasswordCase: UseCaseChangePassword,
    private val loginCase: UseCaseLogin,
    private val resetPasswordCase: UseCaseResetPassword,
    private val saveCase: UseCaseSave,
    private val verifyCodCase: UseCaseVerifyCod,
) {
    fun changePassword(resetSenha: ResetSenha): Flow<Resource<Boolean>> {
        return changePasswordCase(resetSenha)
    }

    fun login(login: Login): Flow<Resource<Token>> {
        return loginCase(login)
    }

    fun resetPassword(email: String): Flow<Resource<Boolean>> {
        return resetPasswordCase(email)
    }

    fun save(usuario: Usuario): Flow<Resource<Usuario>> {
        return saveCase(usuario)
    }

    fun verifyCod(verificarCod: VerificarCod): Flow<Resource<Boolean>> {
        return verifyCodCase(verificarCod)
    }
}