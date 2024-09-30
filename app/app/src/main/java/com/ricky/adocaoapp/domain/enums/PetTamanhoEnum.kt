package com.ricky.adocaoapp.domain.enums

import com.ricky.adocaoapp.domain.enums.EnumWithValue

enum class PetTamanhoEnum(override val value: String) : EnumWithValue {
    PEQUENO("Pequeno"),
    MEDIO("Médio"),
    GRANDE("Grande"),

}