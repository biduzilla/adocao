package com.ricky.adocao.dto

import jakarta.persistence.Column
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
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
