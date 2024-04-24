package com.ricky.adocao.repository

import com.ricky.adocao.models.Role
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface RoleRepository : JpaRepository<Role, Int> {
    fun findByNome(nome: String): Optional<Role>
}