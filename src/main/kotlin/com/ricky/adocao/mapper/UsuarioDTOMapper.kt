package com.ricky.adocao.mapper

import com.ricky.adocao.dto.UsuarioDTO
import com.ricky.adocao.models.Usuario
import org.springframework.stereotype.Component

@Component
class UsuarioDTOMapper:Mapper<Usuario, UsuarioDTO> {
    override fun map(t: Usuario): UsuarioDTO {
        return UsuarioDTO(
            id= t.id,
            nome = t.nome,
            login = t.login,
            email = t.email,
            senha = "",
            telefone = t.telefone
        )
    }

}