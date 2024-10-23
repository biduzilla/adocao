package com.ricky.adocao.service.impl

import com.ricky.adocao.dto.LoginDTO
import com.ricky.adocao.dto.TokenDTO
import com.ricky.adocao.exception.*
import com.ricky.adocao.models.Usuario
import com.ricky.adocao.repository.UsuarioRepository
import com.ricky.adocao.security.JwtService
import com.ricky.adocao.service.RoleService
import com.ricky.adocao.service.UserDetail
import com.ricky.adocao.service.UsuarioService
import com.ricky.adocao.utils.I18n
import org.springframework.beans.BeanUtils
import org.springframework.context.annotation.Lazy
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.regex.Matcher
import java.util.regex.Pattern
import kotlin.random.Random

@Service
class UsuarioServiceImpl(
    private val usuarioRepository: UsuarioRepository,
    private val i18n: I18n,
    @Lazy private val passwordEncoder: PasswordEncoder,
    private val jwtService: JwtService,
    private val roleService: RoleService,
) : UsuarioService, UserDetailsService {
    override fun findAll(pageable: Pageable): Page<Usuario> {
        return usuarioRepository.findAll(pageable)
    }

    override fun login(loginDTO: LoginDTO): TokenDTO {
        val usuario = usuarioRepository.findByEmail(loginDTO.login)
            .orElseThrow { throw NotFoundException(i18n.getMessage("usuario.nao.encotrado")) }

        val usuarioAutentificado = autentificar(usuario, loginDTO.senha)
        val token = jwtService.generateToken(usuarioAutentificado)

        return TokenDTO(token = token, idUser = usuario.id, nome = usuario.nome)
    }

    private fun autentificar(usuario: Usuario, senha: String): UserDetails {
        val userDetail = loadUserByUsername(usuario.email)
        if (!passwordEncoder.matches(senha, userDetail.password)) {
            throw SenhaIncorretaException(i18n.getMessage("error.senha.invalida"))
        }

        return userDetail
    }

    override fun findById(idUser: String): Usuario {
        return usuarioRepository.findById(idUser)
            .orElseThrow { NotFoundException(i18n.getMessage("usuario.nao.encotrado")) }
    }

    override fun update(usuario: Usuario): Usuario {
        val user = findById(usuario.id)
        BeanUtils.copyProperties(usuario, user)
        return save(user, true)
    }


    override fun save(usuario: Usuario, verificar: Boolean): Usuario {
        if (verificar) {
            if (usuarioRepository.existsByEmail(usuario.email)) {
                throw EmailJaCadastradoException(i18n.getMessage("error.email.cadastrado"))
            }

            if (!validEmail(usuario.email)) {
                throw EmailInvalidoException(i18n.getMessage("error.email.invalido"))
            }

            verificarSenha(usuario.senha)

            val role = roleService.findByNome("USER")
            usuario.senha = passwordEncoder.encode(usuario.senha)
            usuario.roles = listOf(role)
        }

        return usuarioRepository.save(usuario)
    }

    override fun delete(usuario: Usuario) {
        usuarioRepository.delete(usuario)
    }

    override fun deleteById(idUsuario: String) {
        usuarioRepository.deleteById(idUsuario)
    }

    override fun findByEmail(login: String): Usuario {
        return usuarioRepository.findByEmail(login)
            .orElseThrow { NotFoundException(i18n.getMessage("usuario.nao.encotrado")) }
    }

    override fun gerarCodVerificacao(): Int {
        var cod: Int
        do {
            cod = geradorCodigo()
        } while (usuarioRepository.existsByCodVerificacao(cod))
        return cod
    }

    override fun findByCodVerificacao(cod: Int): Usuario {
        return usuarioRepository.findByCodVerificacao(cod)
            .orElseThrow { NotFoundException(i18n.getMessage("usuario.nao.encotrado")) }
    }

    override fun alterarSenha(email: String, senha: String, cod: Int) {
        val user = usuarioRepository.findByEmailAndCodVerificacao(email, cod)
            .orElseThrow { NotFoundException(i18n.getMessage("usuario.nao.encotrad")) }
        verificarSenha(senha)
        user.codVerificacao = 0
        user.senha = passwordEncoder.encode(senha)
        usuarioRepository.save(user)
    }

    override fun verificarCod(cod: Int, email: String) {
        if (!usuarioRepository.existsByCodVerificacaoAndEmail(cod, email)) {
            throw CodVerificacaoInvalidoException(i18n.getMessage("cod.verificacao.invalido"))
        }
    }

    override fun refreshToken(tokenDTO: TokenDTO): TokenDTO {
        val user = findById(tokenDTO.idUser)
        val userDetail = loadUserByUsername(user.email)
        val token = jwtService.generateToken(userDetail)
        return TokenDTO(
            token = token,
            idUser = tokenDTO.idUser,
            nome = tokenDTO.nome
        )
    }

    override fun findUsuariosBySenderId(userId: String): List<Usuario> {
        return usuarioRepository.findDistinctUsuariosBySenderIdOrRecipientId(userId)
    }

    private fun verificarSenha(senha: String) {
        if (senha.toCharArray().size <= 7) {
            throw SenhaCurtaException(i18n.getMessage("error.senha.curta"))
        }
    }

    private fun geradorCodigo(): Int {
        val random = Random(System.currentTimeMillis())
        return random.nextInt(100000, 1000000)
    }

    override fun loadUserByUsername(username: String?): UserDetails {
        val usuario =
            usuarioRepository.loadByEmail(username) ?: throw NotFoundException(i18n.getMessage("usuario.nao.encotrado"))

        return UserDetail(usuario)
    }

    private fun validEmail(email: String): Boolean {
        val regexPattern = ("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$")
        val pattern: Pattern = Pattern.compile(regexPattern, Pattern.CASE_INSENSITIVE)
        val matcher: Matcher = pattern.matcher(email)
        return matcher.matches()
    }
}