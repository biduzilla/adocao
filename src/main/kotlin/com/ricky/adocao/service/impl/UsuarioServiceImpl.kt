package com.ricky.adocao.service.impl

import com.ricky.adocao.exception.NotFoundException
import com.ricky.adocao.models.Usuario
import com.ricky.adocao.repository.UsuarioRepository
import com.ricky.adocao.service.UsuarioService
import com.ricky.adocao.utils.I18n
import org.springframework.beans.BeanUtils
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class UsuarioServiceImpl(
    private val usuarioRepository: UsuarioRepository,
    private val i18n: I18n,
) : UsuarioService {
    override fun findAll(pageable: Pageable): Page<Usuario> {
        return usuarioRepository.findAll(pageable)
    }

    override fun findById(idUser: String): Usuario {
        return usuarioRepository.findById(idUser)
            .orElseThrow { NotFoundException(i18n.getMessage("usuario.nao.encotrado")) }
    }

    override fun update(usuario: Usuario): Usuario {
        val user = findById(usuario.id)
        BeanUtils.copyProperties(usuario, user)
        return save(user)
    }

    override fun save(usuario: Usuario): Usuario {
        return usuarioRepository.save(usuario)
    }

    override fun delete(usuario: Usuario) {
        usuarioRepository.delete(usuario)
    }

    override fun deleteById(idUsuario: String) {
        usuarioRepository.deleteById(idUsuario)
    }
}