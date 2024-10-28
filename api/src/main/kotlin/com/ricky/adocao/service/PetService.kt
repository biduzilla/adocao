package com.ricky.adocao.service

import com.ricky.adocao.dto.FiltroSearchDTO
import com.ricky.adocao.models.Pet
import com.ricky.adocao.models.Usuario
import org.springframework.data.domain.Page
import org.springframework.data.repository.query.Param

interface PetService {
    fun findAll(
        search: String?,
        orderBy: String?,
        page: Int,
        qtd: Int,
        filtro: FiltroSearchDTO
    ): Page<Pet>

    fun findById(idPet: String): Pet
    fun findByUser(
        userId: String,
        orderBy: String?,
        page: Int,
        qtd: Int,
    ): Page<Pet>

    fun findUsuarioByPet(pet: Pet): Usuario
    fun update(pet: Pet): Pet
    fun save(pet: Pet, userId: String): Pet
    fun delete(pet: Pet)
    fun deleteAll(pets: List<Pet>)
    fun deleteById(idPet: String)
    fun findByUserIdList(id:String): List<Pet>
}