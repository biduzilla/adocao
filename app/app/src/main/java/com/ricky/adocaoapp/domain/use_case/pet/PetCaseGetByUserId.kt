package com.ricky.adocaoapp.domain.use_case.pet

import com.google.gson.Gson
import com.ricky.adocaoapp.domain.models.ErrorRequest
import com.ricky.adocaoapp.domain.models.PagePet
import com.ricky.adocaoapp.domain.repository.PetRepository
import com.ricky.adocaoapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class PetCaseGetByUserId @Inject constructor(private val repository: PetRepository) {
    operator fun invoke(
        page: Int = 0,
        orderBy: String?,
        qtd: Int = 15,
        userId: String
    ): Flow<Resource<PagePet>> = flow {
        try {
            emit(Resource.Loading())

            repository.getByUserId(
                page = page,
                orderBy = orderBy,
                qtd = qtd,
                userId = userId
            ).let { result ->
                if (result.isSuccessful) {
                    result.body()?.let { pets ->
                        emit(Resource.Success(pets))
                    } ?: run {
                        emit(Resource.Error("Error inesperado"))
                    }
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