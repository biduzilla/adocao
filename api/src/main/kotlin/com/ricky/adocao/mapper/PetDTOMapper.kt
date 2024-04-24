package com.ricky.adocao.mapper

import com.ricky.adocao.dto.PetDTO
import com.ricky.adocao.models.Pet
import com.ricky.adocao.service.PetService
import org.springframework.stereotype.Component

@Component
class PetDTOMapper(
    private val petService: PetService,
    private val usuarioDTOMapper: UsuarioDTOMapper,
) : Mapper<Pet, PetDTO> {
    override fun map(t: Pet): PetDTO {
        val usuario = petService.findUsuarioByPet(t)
        return PetDTO(
            id = t.id,
            nome = t.nome,
            idade = t.idade,
            localizacao = t.localizacao,
            lat = t.lat,
            long = t.long,
            usuario = usuarioDTOMapper.map(usuario),
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