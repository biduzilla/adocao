package com.ricky.adocaoapp.data.repository

import com.ricky.adocaoapp.data.network.api.PetApi
import com.ricky.adocaoapp.domain.models.FiltroSearch
import com.ricky.adocaoapp.domain.models.Pet
import com.ricky.adocaoapp.domain.repository.PetRepository
import retrofit2.Response
import javax.inject.Inject

class PetRepositoryImpl @Inject constructor(private val api: PetApi) : PetRepository {
    override suspend fun getAll(
        page: Int,
        search: String?,
        orderBy: String?,
        qtd: Int,
        filtros: FiltroSearch
    ): Response<Pet> = api.getAll(page, search, orderBy, qtd, filtros)

    override suspend fun getById(id: String): Response<Pet> = api.getById(id)

    override suspend fun save(pet: Pet): Response<Pet> = api.save(pet)

    override suspend fun update(pet: Pet): Response<Pet> = api.update(pet)

    override suspend fun deleteById(id: String): Response<Void> = api.deleteById(id)
}