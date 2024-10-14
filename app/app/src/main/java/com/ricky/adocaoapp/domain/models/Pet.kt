package com.ricky.adocaoapp.domain.models

import com.ricky.adocao.enums.PetTipoAnimalEnum
import com.ricky.adocaoapp.domain.enums.PetCidadeEnum
import com.ricky.adocaoapp.domain.enums.PetGeneroEnum
import com.ricky.adocaoapp.domain.enums.PetIdadeEnum
import com.ricky.adocaoapp.domain.enums.PetStatusEnum
import com.ricky.adocaoapp.domain.enums.PetTamanhoEnum

data class Pet(
    var id: String = "",
    var nome: String = "",
    var idade: PetIdadeEnum = PetIdadeEnum.ADULTO,
    var localizacao: PetCidadeEnum = PetCidadeEnum.NUCLEO_BANDEIRANTE,
    var lat: Double = 0.0,
    var long: Double = 0.0,
    var usuario: Usuario = Usuario(),
    var descricao: String = "",
    var genero: PetGeneroEnum = PetGeneroEnum.FEMEA,
    val dataPublicacao: String="",
    var foto: String="",
    var status: PetStatusEnum = PetStatusEnum.ACHADO,
    var tipoAnimal: PetTipoAnimalEnum = PetTipoAnimalEnum.CACHORRO,
    var tamanho: PetTamanhoEnum = PetTamanhoEnum.MEDIO,
    var donoId: String = "",
    var distancia: String = ""
)
