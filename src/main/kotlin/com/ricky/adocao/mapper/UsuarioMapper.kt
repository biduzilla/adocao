package com.ricky.adocao.mapper

import com.ricky.adocao.dto.UsuarioDTO
import com.ricky.adocao.models.Usuario
import org.springframework.stereotype.Component

@Component
class UsuarioMapper : Mapper<UsuarioDTO, Usuario> {
    override fun map(t: UsuarioDTO): Usuario {
        return Usuario(
            id = t.id,
            nome = t.nome,
            login = t.login,
            senha = t.senha,
            email = t.email,
            telefone = t.telefone
        )
    }
}