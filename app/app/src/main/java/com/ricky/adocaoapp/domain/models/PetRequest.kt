package com.ricky.adocaoapp.domain.models

import com.ricky.adocao.enums.PetTipoAnimalEnum
import com.ricky.adocaoapp.domain.enums.PetCidadeEnum
import com.ricky.adocaoapp.domain.enums.PetGeneroEnum
import com.ricky.adocaoapp.domain.enums.PetIdadeEnum
import com.ricky.adocaoapp.domain.enums.PetStatusEnum
import com.ricky.adocaoapp.domain.enums.PetTamanhoEnum

data class PetRequest(
    var id: String = "",
    var nome: String = "",
    var idade: PetIdadeEnum = PetIdadeEnum.ADULTO,
    var localizacao: PetCidadeEnum = PetCidadeEnum.NUCLEO_BANDEIRANTE,
    var lat: Double = 0.0,
    var long: Double = 0.0,
    var descricao: String = "",
    var genero: PetGeneroEnum = PetGeneroEnum.FEMEA,
    var foto: ByteArray= ByteArray(0),
    var status: PetStatusEnum = PetStatusEnum.ACHADO,
    var tipoAnimal: PetTipoAnimalEnum = PetTipoAnimalEnum.CACHORRO,
    var tamanho: PetTamanhoEnum = PetTamanhoEnum.MEDIO,
    var donoId: String = "",
    var distancia: String = ""
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PetRequest

        if (id != other.id) return false
        if (nome != other.nome) return false
        if (idade != other.idade) return false
        if (localizacao != other.localizacao) return false
        if (lat != other.lat) return false
        if (long != other.long) return false
        if (descricao != other.descricao) return false
        if (genero != other.genero) return false
        if (!foto.contentEquals(other.foto)) return false
        if (status != other.status) return false
        if (tipoAnimal != other.tipoAnimal) return false
        if (tamanho != other.tamanho) return false
        if (donoId != other.donoId) return false
        if (distancia != other.distancia) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + nome.hashCode()
        result = 31 * result + idade.hashCode()
        result = 31 * result + localizacao.hashCode()
        result = 31 * result + lat.hashCode()
        result = 31 * result + long.hashCode()
        result = 31 * result + descricao.hashCode()
        result = 31 * result + genero.hashCode()
        result = 31 * result + foto.contentHashCode()
        result = 31 * result + status.hashCode()
        result = 31 * result + tipoAnimal.hashCode()
        result = 31 * result + tamanho.hashCode()
        result = 31 * result + donoId.hashCode()
        result = 31 * result + distancia.hashCode()
        return result
    }
}
