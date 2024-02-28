package com.ricky.adocao.mapper

import com.ricky.adocao.dto.RegisterDTO
import com.ricky.adocao.models.Usuario
import org.springframework.stereotype.Component

@Component
class RegisterToUsuario:Mapper<RegisterDTO, Usuario> {
    override fun map(t: RegisterDTO): Usuario {
        return Usuario(
            nome = t.nome,
            login = t.login,
            senha = t.senha,
            email = t.email,
            telefone = t.telefone
        )
    }
}