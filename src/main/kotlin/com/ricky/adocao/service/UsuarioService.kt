package com.ricky.adocao.service

import com.ricky.adocao.exception.NotFoundException
import com.ricky.adocao.mapper.UsuarioDTOMapper
import com.ricky.adocao.models.Usuario
import com.ricky.adocao.repository.UsuarioRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class UsuarioService(
    private val usuarioRepository: UsuarioRepository,
    private val usuarioMapper: UsuarioDTOMapper
) {
    fun findAll(pageable: Pageable): Page<Usuario> {
        return usuarioRepository.findAll(pageable)
    }

    fun findById(idUser: String): Usuario {
        return usuarioRepository.findById(idUser).orElseThrow { NotFoundException("usuario.nao.encotrado") }
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