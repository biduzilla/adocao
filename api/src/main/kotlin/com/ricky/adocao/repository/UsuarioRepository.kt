package com.ricky.adocao.repository

import com.ricky.adocao.models.Usuario
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UsuarioRepository : JpaRepository<Usuario, String> {
    fun findByEmail(email: String?): Usuario?
    fun existsByEmail(email: String): Boolean
    fun findByEmail(email: String): Optional<Usuario>
    fun findByEmailAndCodVerificacao(email: String, cod:Int): Optional<Usuario>
    fun findByCodVerificacao(id: Int): Optional<Usuario>
    fun existsByCodVerificacaoAndEmail(id: Int, email: String): Boolean
    fun existsByCodVerificacao(id: Int): Boolean
}