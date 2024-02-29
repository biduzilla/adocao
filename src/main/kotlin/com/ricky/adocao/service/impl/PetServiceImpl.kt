package com.ricky.adocao.service.impl

import com.ricky.adocao.exception.NotFoundException
import com.ricky.adocao.models.Pet
import com.ricky.adocao.repository.PetRepository
import com.ricky.adocao.service.PetService
import com.ricky.adocao.utils.I18n
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class PetServiceImpl(
    private val petRepository: PetRepository,
    private val i18n: I18n
) : PetService {
    override fun findAll(search: String?, pageable: Pageable): Page<Pet> {
        search?.let {
            return petRepository.findAllByNomeContaining(nome = search, pageable = pageable)
        } ?: run {
            return petRepository.findAll(pageable)
        }
    }

    override fun findById(idPet: String): Pet {
        return petRepository.findById(idPet).orElseThrow { NotFoundException(i18n.getMessage("pet.nao.encontrado")) }
    }

    override fun save(pet: Pet): Pet {
        return petRepository.save(pet)
    }

    override fun delete(pet: Pet) {
        petRepository.delete(pet)
    }

    override fun deleteById(idPet: String) {
        petRepository.deleteById(idPet)
    }

}