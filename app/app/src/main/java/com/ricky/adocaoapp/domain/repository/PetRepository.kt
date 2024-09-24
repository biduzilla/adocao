package com.ricky.adocaoapp.domain.repository

import com.ricky.adocaoapp.domain.models.FiltroSearch
import com.ricky.adocaoapp.domain.models.Pet
import retrofit2.Response

interface PetRepository {
    suspend fun getAll(
        page: Int = 0,
        search: String?,
        orderBy: String?,
        qtd: Int = 15, filtros: FiltroSearch
    ): Response<Pet>

    suspend fun getById(id: String): Response<Pet>

    suspend fun save(pet: Pet): Response<Pet>

    suspend fun update(pet: Pet): Response<Pet>

    suspend fun deleteById(id: String): Response<Void>
}