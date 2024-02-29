package com.ricky.adocao.repository

import com.ricky.adocao.models.Pet
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface PetRepository : JpaRepository<Pet, String> {

    fun findAllByNomeContaining(nome:String, pageable:Pageable): Page<Pet>
}