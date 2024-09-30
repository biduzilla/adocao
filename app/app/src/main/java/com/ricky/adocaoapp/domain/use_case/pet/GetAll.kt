package com.ricky.adocaoapp.domain.use_case.pet

import com.google.gson.Gson
import com.ricky.adocaoapp.domain.models.ErrorRequest
import com.ricky.adocaoapp.domain.models.FiltroSearch
import com.ricky.adocaoapp.domain.models.Pet
import com.ricky.adocaoapp.domain.models.ResetSenha
import com.ricky.adocaoapp.domain.repository.PetRepository
import com.ricky.adocaoapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetAll @Inject constructor(private val repository: PetRepository) {
    operator fun invoke(
        page: Int = 0,
        search: String?,
        orderBy: String?,
        qtd: Int = 15,
        filtros: FiltroSearch
    ): Flow<Resource<List<Pet>?>> = flow {
        try {
            emit(Resource.Loading())

            repository.getAll(
                page = page,
                search = search,
                orderBy = orderBy,
                qtd = qtd,
                filtros = filtros
            ).let { result ->
                if (result.isSuccessful && result.body() != null) {
                    emit(Resource.Success(result.body()))
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