package com.ricky.adocao.dto

import lombok.Getter
import lombok.Setter

@Getter
@Setter
data class UsuarioDTO(
    var id: String = "",
    var nome: String = "",
    var login: String = "",
    var senha:String = "",
    var email: String = "",
    var telefone: String = "",
)
