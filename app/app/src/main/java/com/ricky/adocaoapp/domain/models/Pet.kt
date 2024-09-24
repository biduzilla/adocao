package com.ricky.adocaoapp.domain.models

import com.ricky.adocao.enums.PetCidadeEnum
import com.ricky.adocao.enums.PetGeneroEnum
import com.ricky.adocao.enums.PetIdadeEnum
import com.ricky.adocao.enums.PetStatusEnum
import com.ricky.adocao.enums.PetTamanhoEnum
import com.ricky.adocao.enums.PetTipoAnimalEnum
import java.time.LocalDate

data class Pet(
    var id: String = "",
    var nome: String = "",
    var idade: PetIdadeEnum = PetIdadeEnum.ADULTO,
    var localizacao: PetCidadeEnum = PetCidadeEnum.NUCLEO_BANDEIRANTE,
    var lat: Long,
    var long: Long,
    var usuario: Usuario = Usuario(),
    var descricao: String = "",
    var genero: PetGeneroEnum = PetGeneroEnum.FEMEA,
    var dataPublicacao: LocalDate = LocalDate.now(),
    var foto: ByteArray = ByteArray(0),
    var status: PetStatusEnum = PetStatusEnum.ACHADO,
    var tipoAnimal: PetTipoAnimalEnum = PetTipoAnimalEnum.CACHORRO,
    var tamanho: PetTamanhoEnum = PetTamanhoEnum.MEDIO,
    var donoId:String = "",
)
