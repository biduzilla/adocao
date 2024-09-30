package com.ricky.adocaoapp.domain.enums

import com.ricky.adocaoapp.domain.enums.EnumWithValue

enum class PetIdadeEnum(override val value: String):EnumWithValue {
    FILHOTE("Filhote"),
    ADULTO("Adulto"),
    IDOSO("Idoso");
}