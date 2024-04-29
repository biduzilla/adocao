package com.ricky.adocaoapp.domain.use_case.user

import com.ricky.adocaoapp.domain.repository.UserRepository
import com.ricky.adocaoapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class UseCaseResetPassword @Inject constructor(private val repository: UserRepository) {

    operator fun invoke(email: String): Flow<Resource<Boolean>> = flow {
        try {
            emit(Resource.Loading())

            repository.resetPassword(email).let { result ->
                if (result.isSuccessful) {
                    emit(Resource.Success(true))
                } else {
                    val errorBody = result.errorBody().toString()
                    emit(Resource.Error("Error $errorBody"))
                }
            }
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "Error inesperado"))
        } catch (e: IOException) {
            emit(Resource.Error("Cheque sua conexão com a internet"))
        }
    }
}