package com.ricky.adocao.repository

import com.ricky.adocao.models.Usuario
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UsuarioRepository : JpaRepository<Usuario, String> {
    fun findByLogin(login: String?): Usuario?
    fun existsByLogin(login: String): Boolean
    fun existsByEmail(email: String): Boolean
    fun findByLoginOrEmail(login: String, email: String): Optional<Usuario>
    fun findByEmail(email: String): Optional<Usuario>

    fun findByCodVerificacao(id: Int): Optional<Usuario>
    fun existsByCodVerificacaoAndEmail(id: Int, email: String): Boolean
    fun existsByCodVerificacao(id: Int): Boolean
}