package com.ricky.adocao.service

import com.ricky.adocao.models.Role

interface RoleService {
    fun findAll(): List<Role>
    fun findByNome(nome: String): Role
    fun update(role: Role): Role
    fun save(role: Role): Role
    fun delete(role: Role)
    fun deleteById(idRole: Int)
}