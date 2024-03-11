package com.ricky.adocao.models

import com.ricky.adocao.enums.RoleEnum
import jakarta.persistence.*
import lombok.Builder
import lombok.NoArgsConstructor
import org.springframework.security.core.GrantedAuthority

@Entity(name = "USUARIO")
data class Usuario(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "USER_ID")
    var id: String = "",

    @Column(name = "NOME", length = 50)
    var nome: String = "",

    @Column(name = "LOGIN", length = 50)
    var login: String = "",

    @Column(name = "SENHA")
    var senha: String = "",

    @Column(name = "EMAIL", length = 50)
    var email: String = "",

    @Column(name = "TELEFONE", length = 10)
    var telefone: String = "",

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "USUARIO_ROLE",
        joinColumns = [JoinColumn(name = "USER_ID")],
        inverseJoinColumns = [JoinColumn(name = "ROLE_ID")]
    )
    var roles: List<Role> = mutableListOf()

)
