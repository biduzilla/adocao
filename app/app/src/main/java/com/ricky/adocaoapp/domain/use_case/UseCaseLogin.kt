package com.ricky.adocaoapp.domain.use_case

import android.util.Log
import com.ricky.adocaoapp.domain.models.Login
import com.ricky.adocaoapp.domain.models.Token
import com.ricky.adocaoapp.domain.repository.UserRepository
import com.ricky.adocaoapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UseCaseLogin @Inject constructor(private val userRepository: UserRepository) {

    suspend operator fun invoke(login: Login): Token? {
        try {
            userRepository.login(login).let { result ->
                result.body()?.let { token ->
                    return token
                }
            }
        } catch (e: Exception) {
            Log.i("infoteste", e.localizedMessage ?: "Error inesperado")
        }
        return null
    }
}