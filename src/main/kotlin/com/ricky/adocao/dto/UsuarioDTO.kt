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
    val id: String? = null,
    val nome: String,
    val login: String,
    val email: String,
    val telefone: String,
)
