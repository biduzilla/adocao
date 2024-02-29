package com.ricky.adocao.mapper

import com.ricky.adocao.dto.PetDTO
import com.ricky.adocao.models.Pet

class PetDTOMapper():Mapper<Pet,PetDTO> {
    override fun map(t: Pet): PetDTO {
        return PetDTO()
    }
}