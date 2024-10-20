package com.ricky.adocaoapp.data.repository

import com.ricky.adocaoapp.data.network.api.PetApi
import com.ricky.adocaoapp.domain.models.FiltroSearch
import com.ricky.adocaoapp.domain.models.PagePet
import com.ricky.adocaoapp.domain.models.Pet
import com.ricky.adocaoapp.domain.models.PetRequest
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
    ): Response<PagePet> = api.getAll(
        page, search, orderBy, qtd,
        isDog = filtros.isDog,
        isCat = filtros.isCat,
        isAchado = filtros.isAchado,
        isAdotar = filtros.isAdotar,
        isPerdido = filtros.isPerdido,
        isGrande = filtros.isGrande,
        isMedio = filtros.isMedio,
        isPequeno = filtros.isPequeno,
        isMacho = filtros.isMacho,
        isFemea = filtros.isFemea,
        isFilhote = filtros.isFilhote,
        isAdulto = filtros.isAdulto,
        isIdoso = filtros.isIdoso
    )

    override suspend fun getByUserId(
        page: Int,
        orderBy: String?,
        qtd: Int,
        userId: String
    ): Response<PagePet> = api.getByUserId(
        page = page,
        orderBy = orderBy,
        qtd = qtd,
        userId = userId
    )

    override suspend fun getById(id: String): Response<Pet> = api.getById(id)

    override suspend fun save(pet: PetRequest): Response<Pet> = api.save(pet)

    override suspend fun update(pet: PetRequest): Response<Pet> = api.update(pet)

    override suspend fun deleteById(id: String): Response<Void> = api.deleteById(id)
}