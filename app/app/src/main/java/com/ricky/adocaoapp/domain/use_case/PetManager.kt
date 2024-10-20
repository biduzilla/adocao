package com.ricky.adocaoapp.domain.use_case

import com.ricky.adocaoapp.domain.models.FiltroSearch
import com.ricky.adocaoapp.domain.models.PagePet
import com.ricky.adocaoapp.domain.models.Pet
import com.ricky.adocaoapp.domain.models.PetRequest
import com.ricky.adocaoapp.domain.use_case.pet.PetCaseDeleteById
import com.ricky.adocaoapp.domain.use_case.pet.PetCaseGetAll
import com.ricky.adocaoapp.domain.use_case.pet.PetCaseGetById
import com.ricky.adocaoapp.domain.use_case.pet.PetCaseGetByUserId
import com.ricky.adocaoapp.domain.use_case.pet.PetCasePost
import com.ricky.adocaoapp.domain.use_case.pet.PetCaseUpdate
import com.ricky.adocaoapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PetManager @Inject constructor(
    private val petCaseGetAll: PetCaseGetAll,
    private val petCaseGetById: PetCaseGetById,
    private val petCasePost: PetCasePost,
    private val petCaseUpdate: PetCaseUpdate,
    private val petCaseDeleteById: PetCaseDeleteById,
    private val petCaseGetByUserId: PetCaseGetByUserId
) {
    fun getAll(
        page: Int = 0,
        search: String?,
        orderBy: String? = null,
        qtd: Int = 15,
        filtros: FiltroSearch
    ): Flow<Resource<PagePet>> {
        return petCaseGetAll(
            page = page,
            search = search,
            orderBy = orderBy,
            qtd = qtd,
            filtros = filtros
        )
    }

    fun getByUserId(
        page: Int = 0,
        orderBy: String? = null,
        qtd: Int = 15,
        userId: String,
    ): Flow<Resource<PagePet>> {
        return petCaseGetByUserId(
            page = page,
            orderBy = orderBy,
            qtd = qtd,
            userId = userId
        )
    }

    fun getById(id: String): Flow<Resource<Pet>> {
        return petCaseGetById(id)
    }

    fun save(pet: PetRequest): Flow<Resource<Pet>> {
        return petCasePost(pet)
    }

    fun update(pet: PetRequest): Flow<Resource<Pet>> {
        return petCaseUpdate(pet)
    }

    fun deleteById(id: String): Flow<Resource<Boolean>> {
        return petCaseDeleteById(id)
    }

}