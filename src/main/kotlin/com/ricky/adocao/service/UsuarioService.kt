package com.ricky.adocao.service

import com.ricky.adocao.exception.NotFoundException
import com.ricky.adocao.models.Usuario
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface UsuarioService {
    fun findAll(pageable: Pageable): Page<Usuario>
    fun findById(idUser: String): Usuario
    fun save(usuario: Usuario): Usuario
    fun delete(usuario: Usuario)
    fun deleteById(idUsuario: String)
}