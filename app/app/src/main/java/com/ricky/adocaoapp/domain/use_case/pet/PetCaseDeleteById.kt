package com.ricky.adocaoapp.domain.use_case.pet

import com.google.gson.Gson
import com.ricky.adocaoapp.domain.models.ErrorRequest
import com.ricky.adocaoapp.domain.repository.PetRepository
import com.ricky.adocaoapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class PetCaseDeleteById @Inject constructor(private val repository: PetRepository) {
    operator fun invoke(id: String): Flow<Resource<Boolean>> = flow {
        try {
            emit(Resource.Loading())

            repository.deleteById(id).let { result ->
                if (result.isSuccessful) {
                    emit(Resource.Success(true))
                } else {
                    val error =
                        Gson().fromJson(result.errorBody()?.charStream(), ErrorRequest::class.java)
                    emit(Resource.Error(error?.message ?: "Error desconhecido"))
                }
            }
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "Error inesperado"))
        } catch (e: IOException) {
            emit(Resource.Error("Cheque sua conexão com a internet"))
        }
    }
}