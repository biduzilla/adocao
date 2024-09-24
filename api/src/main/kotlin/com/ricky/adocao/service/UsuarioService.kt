package com.ricky.adocao.service

import com.ricky.adocao.dto.LoginDTO
import com.ricky.adocao.dto.TokenDTO
import com.ricky.adocao.models.Usuario
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface UsuarioService {
    fun findAll(pageable: Pageable): Page<Usuario>
    fun login(loginDTO: LoginDTO): TokenDTO
    fun findById(idUser: String): Usuario
    fun update(usuario: Usuario): Usuario
    fun save(usuario: Usuario, verificar: Boolean): Usuario
    fun delete(usuario: Usuario)
    fun deleteById(idUsuario: String)
    fun findByEmail(login: String): Usuario
    fun gerarCodVerificacao(): Int
    fun findByCodVerificacao(cod: Int): Usuario
    fun alterarSenha(email: String, senha: String, cod:Int)
    fun verificarCod(cod: Int, email: String)
    fun refreshToken(tokenDTO: TokenDTO):TokenDTO
}