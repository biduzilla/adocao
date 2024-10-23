package com.ricky.adocao.repository

import com.ricky.adocao.models.Usuario
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.*

interface UsuarioRepository : JpaRepository<Usuario, String> {
    @Query("FROM USUARIO u WHERE :email IS NULL OR u.email = :email")
    fun loadByEmail(@Param("email") email: String?): Usuario?
    fun existsByEmail(email: String): Boolean
    fun findByEmail(@Param("email") email: String): Optional<Usuario>
    fun findByEmailAndCodVerificacao(email: String, cod: Int): Optional<Usuario>
    fun findByCodVerificacao(id: Int): Optional<Usuario>
    fun existsByCodVerificacaoAndEmail(id: Int, email: String): Boolean
    fun existsByCodVerificacao(id: Int): Boolean

    @Query(
        """
    SELECT DISTINCT u FROM USUARIO u 
    WHERE u.id IN (
        SELECT c.recipientId FROM ChatMessage c 
        WHERE c.senderId = :userId
        AND c.recipientId <> :userId
    )
    OR u.id IN (
        SELECT c.senderId FROM ChatMessage c 
        WHERE c.recipientId = :userId
        AND c.senderId <> :userId
    )
    """
    )
    fun findDistinctUsuariosBySenderIdOrRecipientId(@Param("userId") userId: String): List<Usuario>

}