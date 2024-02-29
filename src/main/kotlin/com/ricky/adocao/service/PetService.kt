package com.ricky.adocao.service

import com.ricky.adocao.models.Pet
import com.ricky.adocao.models.Usuario
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface PetService {
    fun findAll(search: String?, pageable: Pageable): Page<Pet>
    fun findById(idPet: String): Pet
    fun findUsuarioByPet(pet: Pet): Usuario
    fun update(pet: Pet): Pet
    fun save(pet: Pet, userId: String): Pet
    fun delete(pet: Pet)
    fun deleteById(idPet: String)
}