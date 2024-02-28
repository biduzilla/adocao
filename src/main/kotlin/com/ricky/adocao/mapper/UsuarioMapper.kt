package com.ricky.adocao.mapper

import com.ricky.adocao.dto.UsuarioDTO
import com.ricky.adocao.models.Usuario
import com.ricky.adocao.service.UsuarioService
import org.springframework.stereotype.Component

@Component
class UsuarioMapper(private val usuarioService: UsuarioService) : Mapper<UsuarioDTO, Usuario> {
    override fun map(t: UsuarioDTO): Usuario {
        return Usuario(
            id = t.id,
            nome = t.nome,
            login = t.login,
            senha = usuarioService.findById(t.id).senha,
            email = t.email,
            telefone = t.telefone
        )
    }
}