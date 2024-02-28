package com.ricky.adocao.models

import jakarta.persistence.*

@Entity(name="USUARIO")
data class Usuario(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "USER_ID")
    val id: String,

    @Column(name = "NOME", length = 50)
    val nome: String,

    @Column(name = "LOGIN", length = 50)
    val login: String,

    @Column(name = "SENHA")
    val senha: String,

    @Column(name = "EMAIL", length = 50)
    val email: String,

    @Column(name = "TELEFONE", length = 10)
    val telefone: String,
)
