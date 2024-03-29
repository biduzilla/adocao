package com.ricky.adocao.dto

import jakarta.validation.constraints.NotBlank

data class LoginDTO(
    @field:NotBlank(message = "{login.obrigatorio}")
    var login: String = "",
    @field:NotBlank(message = "{senha.obrigatorio}")
    var senha: String = ""
)
