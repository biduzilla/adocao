package com.ricky.adocao.dto

import jakarta.validation.constraints.NotBlank

data class UsuarioDTO(
    var id: String = "",
    @field:NotBlank(message = "{nome.obrigatorio}")
    var nome: String = "",
    @field:NotBlank(message = "{login.obrigatorio}")
    var login: String = "",
    @field:NotBlank(message = "{senha.obrigatorio}")
    var senha:String = "",
    @field:NotBlank(message = "{email.obrigatorio}")
    var email: String = "",
    @field:NotBlank(message = "{telefone.obrigatorio}")
    var telefone: String = "",
)
