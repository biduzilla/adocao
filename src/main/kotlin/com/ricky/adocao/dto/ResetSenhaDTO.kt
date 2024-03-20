package com.ricky.adocao.dto

import jakarta.validation.constraints.NotBlank

data class ResetSenhaDTO(
    @field:NotBlank(message = "{cod.obrigatorio}")
    var cod:String = "",
    @field:NotBlank(message = "{senha.obrigatorio}")
    var senha:String = ""
)
