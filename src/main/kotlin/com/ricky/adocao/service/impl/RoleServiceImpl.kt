package com.ricky.adocao.service.impl

import com.ricky.adocao.exception.NotFoundException
import com.ricky.adocao.models.Role
import com.ricky.adocao.repository.RoleRepository
import com.ricky.adocao.service.RoleService
import com.ricky.adocao.utils.I18n
import org.springframework.beans.BeanUtils
import org.springframework.stereotype.Service

@Service
class RoleServiceImpl(
    private val repository: RoleRepository,
    private val i18n: I18n
) : RoleService {
    override fun findAll(): List<Role> = repository.findAll()

    override fun findByNome(nome: String): Role {
        return repository.findByNome(nome)
            .orElseThrow { throw NotFoundException(i18n.getMessage("role.nao.encotrado")) }
    }

    override fun update(role: Role): Role {
        val roleRecuperada = findByNome(role.nome)
        BeanUtils.copyProperties(role, roleRecuperada)
        return save(roleRecuperada)
    }

    override fun save(role: Role): Role = repository.save(role)

    override fun delete(role: Role) = repository.delete(role)

    override fun deleteById(idRole: Int) = repository.deleteById(idRole)
}