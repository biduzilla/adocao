package com.ricky.adocao.models

import jakarta.persistence.*

@Entity(name = "USUARIO")
@Table(name = "USUARIO")
data class Usuario(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "USER_ID")
    var id: String = "",

    @Column(name = "NOME", length = 50)
    var nome: String = "",

    @Column(name = "SENHA")
    var senha: String = "",

    @Column(name = "EMAIL", length = 50)
    var email: String = "",

    @Column(name = "TELEFONE", length = 10)
    var telefone: String = "",

    @Column(name="CODVERIFICACAO")
    var codVerificacao:Int = 0,

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "USUARIO_ROLE",
        joinColumns = [JoinColumn(name = "USER_ID")],
        inverseJoinColumns = [JoinColumn(name = "ROLE_ID")]
    )
    var roles: List<Role> = mutableListOf()

)
