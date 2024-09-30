package com.ricky.adocao.enums

import com.ricky.adocaoapp.domain.enums.EnumWithValue

enum class PetTipoAnimalEnum(override val value: String):EnumWithValue {
    GATO("Gato"),
    CACHORRO("Cachorro");
}