package com.ricky.adocao.service

import com.ricky.adocao.models.Pet
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface PetService {
    fun findAll(search:String?,pageable: Pageable): Page<Pet>
    fun findById(idPet:String):Pet
    fun save(pet: Pet):Pet
    fun delete(pet: Pet)
    fun deleteById(idPet:String)
}