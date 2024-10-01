package com.ricky.adocaoapp.domain.use_case

import com.ricky.adocaoapp.domain.models.FiltroSearch
import com.ricky.adocaoapp.domain.models.PagePet
import com.ricky.adocaoapp.domain.use_case.pet.PetCaseGetAll
import com.ricky.adocaoapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PetManager @Inject constructor(
    private val petCaseGetAll: PetCaseGetAll
) {
    fun getAll(
        page: Int = 0,
        search: String?,
        orderBy: String? = null,
        qtd: Int = 15,
        filtros: FiltroSearch
    ): Flow<Resource<PagePet>> {
        return getAll(
            page = page,
            search = search,
            orderBy = orderBy,
            qtd = qtd,
            filtros = filtros
        )
    }
}