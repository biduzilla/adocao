package com.ricky.adocao.models

import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority

@Entity
data class Role(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROLE_ID")
    private val id: Long,
    @Column(name="NOME", length = 50)
    private val nome: String
) : GrantedAuthority {
    override fun getAuthority(): String = nome
}
