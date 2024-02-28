package com.ricky.adocao.service

import com.ricky.adocao.dto.UsuarioDTO
import com.ricky.adocao.exception.NotFoundException
import com.ricky.adocao.mapper.UsuarioToDTO
import com.ricky.adocao.models.Usuario
import com.ricky.adocao.repository.UsuarioRepository
import org.springframework.stereotype.Service

@Service
class UsuarioService(
    private val usuarioRepository: UsuarioRepository,
    private val usuarioMapper: UsuarioToDTO
) {
    fun findAll(): List<UsuarioDTO> {
        return usuarioRepository.findAll().map(usuarioMapper::map)
    }

    fun findAllById(idUser: String): UsuarioDTO {
        return usuarioRepository.findById(idUser).map(usuarioMapper::map)
            .orElseThrow { NotFoundException("usuario.nao.encotrado") }
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