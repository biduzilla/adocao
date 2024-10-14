package com.ricky.adocao.repository

import com.ricky.adocao.dto.FiltroSearchDTO
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

    @Query(
        "SELECT p FROM Pet p " +
                "WHERE (:#{#filtro.isDog} = false OR p.tipoAnimal = 'CACHORRO') " +
                "AND (:search is null OR p.nome LIKE %:search%) " +
                "AND (:#{#filtro.isCat} = false OR p.tipoAnimal = 'GATO') " +
                "AND (:#{#filtro.isAchado} = false OR p.status = 'ACHADO') " +
                "AND (:#{#filtro.isAdotar} = false OR p.status = 'ADOCAO')"+
                "AND (:#{#filtro.isPerdido} = false OR p.status = 'PERDIDO')"+
                "AND (:#{#filtro.isGrande} = false OR p.tamanho = 'GRANDE')"+
                "AND (:#{#filtro.isMedio} = false OR p.tamanho = 'MEDIO')"+
                "AND (:#{#filtro.isPequeno} = false OR p.tamanho = 'PEQUENO')"+
                "AND (:#{#filtro.isMacho} = false OR p.genero = 'MACHO')"+
                "AND (:#{#filtro.isFemea} = false OR p.genero = 'FEMEA')"+
                "AND (:#{#filtro.isFilhote} = false OR p.idade = 'FILHOTE')"+
                "AND (:#{#filtro.isAdulto} = false OR p.idade = 'ADULTO')"+
                "AND (:#{#filtro.isIdoso} = false OR p.idade = 'IDOSO')"
    )
    fun findAllByFilters(
        @Param("search") search: String?,
        @Param("filtro") filtro: FiltroSearchDTO,  pageable: Pageable
    ): Page<Pet>
}