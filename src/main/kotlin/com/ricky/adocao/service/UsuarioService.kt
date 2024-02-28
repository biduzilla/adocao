package com.ricky.adocao.service

import com.ricky.adocao.exception.NotFoundException
import com.ricky.adocao.mapper.UsuarioDTOMapper
import com.ricky.adocao.models.Usuario
import com.ricky.adocao.repository.UsuarioRepository
import com.ricky.adocao.utils.I18n
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class UsuarioService(
    private val usuarioRepository: UsuarioRepository,
    private val usuarioMapper: UsuarioDTOMapper,
    private val i18n: I18n,
) {
    fun findAll(pageable: Pageable): Page<Usuario> {
        return usuarioRepository.findAll(pageable)
    }

    fun findById(idUser: String): Usuario {
        return usuarioRepository.findById(idUser)
            .orElseThrow { NotFoundException(i18n.getMessage("usuario.nao.encotrado")) }
    }

    fun save(usuario: Usuario): Usuario {
        return usuarioRepository.save(usuario)
    }

    fun delete(usuario: Usuario) {
        usuarioRepository.delete(usuario)
    }

    fun deleteById(idUsuario: String) {
        usuarioRepository.deleteById(idUsuario)
    }
}