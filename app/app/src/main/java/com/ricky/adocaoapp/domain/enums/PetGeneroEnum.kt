package com.ricky.adocao.enums

enum class PetGeneroEnum {
    MACHO,
    FEMEA;

    override fun toString(): String {
        return when(this){
            MACHO -> "PET_GENERO_MACHO"
            FEMEA -> "PET_GENERO_FEMEA"
        }
    }


}