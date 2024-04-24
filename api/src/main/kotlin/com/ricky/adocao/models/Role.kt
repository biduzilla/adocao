package com.ricky.adocao.models

import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority

@Entity
data class Role(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROLE_ID")
    val id: Int,
    @Column(name = "NOME", length = 50)
    val nome: String
) : GrantedAuthority {
    override fun getAuthority(): String = nome
}
