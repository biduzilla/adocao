package com.ricky.adocao.repository

import com.ricky.adocao.models.Pet
import com.ricky.adocao.models.Usuario
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface PetRepository : JpaRepository<Pet, String> {
    @Query("SELECT p.usuario FROM Pet p WHERE p= :pet")
    fun findUsuarioByPet(@Param("pet") pet: Pet): Usuario
    fun findAllByNomeContaining(nome:String, pageable:Pageable): Page<Pet>
}