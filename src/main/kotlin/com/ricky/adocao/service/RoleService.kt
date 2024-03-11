package com.ricky.adocao.service

import com.ricky.adocao.models.Pet
import com.ricky.adocao.models.Role
import com.ricky.adocao.models.Usuario
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface RoleService {
    fun findAll(): List<Role>
    fun findByNome(nome: String): Role
    fun update(role: Role): Role
    fun save(role: Role): Role
    fun delete(role: Role)
    fun deleteById(idRole: Int)
}