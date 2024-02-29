package com.ricky.adocao.mapper

import com.ricky.adocao.dto.PetDTO
import com.ricky.adocao.models.Pet
import com.ricky.adocao.service.PetService

class PetDTOMapper(private val petService: PetService) : Mapper<Pet, PetDTO> {
    override fun map(t: Pet): PetDTO {
        val usuario = petService.findUsuarioByPet(t)
        return PetDTO(
            id = t.id,
            nome = t.nome,
            idade = t.idade,
            localizacao = t.localizacao,
            usuario = usuario,
            descricao = t.descricao,
            genero = t.genero,
            dataPublicacao = t.dataPublicacao,
            foto = t.foto,
            status = t.status,
            tipoAnimal = t.tipoAnimal,
            donoId = usuario.id
        )
    }
}