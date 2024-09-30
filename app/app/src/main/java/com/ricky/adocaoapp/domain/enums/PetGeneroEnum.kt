package com.ricky.adocaoapp.domain.enums

enum class PetGeneroEnum {
    MACHO,
    FEMEA;

    override fun toString(): String {
        return when(this){
            MACHO -> "Macho"
            FEMEA -> "Fêmea"
        }
    }


}