package com.ricky.adocao.mapper

import com.ricky.adocao.dto.PetDTO
import com.ricky.adocao.models.Pet
import com.ricky.adocao.service.UsuarioService
import org.springframework.stereotype.Component

@Component
class PetMapper(private val usuarioService: UsuarioService) : Mapper<PetDTO, Pet> {
    override fun map(t: PetDTO): Pet {
        return Pet(
            id = t.id,
            nome = t.nome,
            idade = t.idade,
            localizacao = t.localizacao,
            lat = t.lat,
            long = t.long,
            usuario = usuarioService.findById(t.donoId),
            descricao = t.descricao,
            genero = t.genero,
            dataPublicacao = t.dataPublicacao,
            foto = t.foto,
            status = t.status,
            tipoAnimal = t.tipoAnimal
        )
    }
}