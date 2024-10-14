package com.ricky.adocao.dto

import com.ricky.adocao.enums.*
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import java.time.LocalDate

data class PetDTO(
    var id: String = "",
    @field:NotBlank(message = "{nome.obrigatorio}")
    var nome: String = "",
    var idade: PetIdadeEnum = PetIdadeEnum.ADULTO,
    @field:NotEmpty(message = "{localizacao.obrigatorio}")
    var localizacao: PetCidadeEnum = PetCidadeEnum.NUCLEO_BANDEIRANTE,
    var lat: Double,
    var long: Double,
    var usuario: UsuarioDTO = UsuarioDTO(),
    @field:NotBlank(message = "{descricao.obrigatorio}")
    var descricao: String = "",
    @field:NotEmpty(message = "{genero.obrigatorio}")
    var genero: PetGeneroEnum = PetGeneroEnum.FEMEA,
    var dataPublicacao: LocalDate = LocalDate.now(),
    var foto: ByteArray = ByteArray(0),
    @field:NotEmpty(message = "{status.obrigatorio}")
    var status: PetStatusEnum = PetStatusEnum.ACHADO,
    @field:NotEmpty(message = "{tipoAnimal.obrigatorio}")
    var tipoAnimal: PetTipoAnimalEnum = PetTipoAnimalEnum.CACHORRO,
    @field:NotEmpty(message = "{tamanhoAnimal.obrigatorio}")
    var tamanho: PetTamanhoEnum = PetTamanhoEnum.MEDIO,
    var donoId:String = "",
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PetDTO

        if (id != other.id) return false
        if (nome != other.nome) return false
        if (idade != other.idade) return false
        if (localizacao != other.localizacao) return false
        if (usuario != other.usuario) return false
        if (descricao != other.descricao) return false
        if (genero != other.genero) return false
        if (dataPublicacao != other.dataPublicacao) return false
        if (!foto.contentEquals(other.foto)) return false
        if (status != other.status) return false
        if (tipoAnimal != other.tipoAnimal) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + nome.hashCode()
        result = 31 * result + idade.hashCode()
        result = 31 * result + localizacao.hashCode()
        result = 31 * result + usuario.hashCode()
        result = 31 * result + descricao.hashCode()
        result = 31 * result + genero.hashCode()
        result = 31 * result + dataPublicacao.hashCode()
        result = 31 * result + foto.contentHashCode()
        result = 31 * result + status.hashCode()
        result = 31 * result + tipoAnimal.hashCode()
        return result
    }
}
