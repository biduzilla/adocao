package com.ricky.adocao.controller

import com.ricky.adocao.dto.*
import com.ricky.adocao.mapper.UsuarioDTOMapper
import com.ricky.adocao.mapper.UsuarioMapper
import com.ricky.adocao.service.EmailService
import com.ricky.adocao.service.UsuarioService
import com.ricky.adocao.utils.CacheConstants
import jakarta.transaction.Transactional
import jakarta.validation.Valid
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/usuario")
@CrossOrigin
class UsuarioController(
    private val usuarioService: UsuarioService,
    private val usuarioDTOMapper: UsuarioDTOMapper,
    private val usuarioMapper: UsuarioMapper,
    private val emailService: EmailService
) {
    @GetMapping("/find-all")
    @Cacheable(CacheConstants.USUARIOS_CACHE)
    fun findAll(
        @RequestParam(required = false, defaultValue = "0") page: Int,
        @PageableDefault(
            size = 10,
        ) paginacao: Pageable
    ): Page<UsuarioDTO> {
        val pageable = PageRequest.of(page, paginacao.pageSize)
        return usuarioService.findAll(pageable).map { usuarioDTOMapper.map(it) }
    }

    @PostMapping("/login")
    fun login(@RequestBody @Valid loginDTO: LoginDTO): TokenDTO {
        return usuarioService.login(loginDTO)
    }

    @PostMapping("/refresh-token")
    fun refreshToken(@RequestBody token: TokenDTO): TokenDTO {
        return usuarioService.refreshToken(token)
    }

    @GetMapping("/get-user/{idUser}")
    fun findById(@PathVariable idUser: String): UsuarioDTO {
        return usuarioDTOMapper.map(usuarioService.findById(idUser))
    }

    @PostMapping("/save")
    @Transactional
    @CacheEvict(value = [CacheConstants.USUARIOS_CACHE], allEntries = true)
    fun insert(@RequestBody @Valid usuarioDTO: UsuarioDTO): ResponseEntity<UsuarioDTO> {
        val userSalvar = usuarioMapper.map(usuarioDTO)
        val user = usuarioService.save(userSalvar, true)
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioDTOMapper.map(user))
    }

    @PutMapping("/update")
    @Transactional
    @CacheEvict(value = [CacheConstants.USUARIOS_CACHE], allEntries = true)
    fun update(@RequestBody @Valid usuarioDTO: UsuarioDTO): ResponseEntity<UsuarioDTO> {
        val usuario = usuarioService.update(usuarioMapper.map(usuarioDTO))
        return ResponseEntity.status(HttpStatus.OK).body(usuarioDTOMapper.map(usuario))
    }

    @DeleteMapping("/delete-user/{idUsuario}")
    @Transactional
    @CacheEvict(value = [CacheConstants.USUARIOS_CACHE], allEntries = true)
    fun deleteById(@PathVariable idUsuario: String) {
        usuarioService.deleteById(idUsuario)
    }

    @PostMapping("/reset-senha/{email}")
    @Transactional
    fun enviarEmailSenha(@PathVariable email: String) {
        val user = usuarioService.findByEmail(email)
        val cod = usuarioService.gerarCodVerificacao()
        user.codVerificacao = cod
        usuarioService.save(usuario = user, verificar = false)
        emailService.sendEmail(cod = cod.toString(), to = user.email)
    }

    @GetMapping("verificar-cod")
    fun verificarCod(@RequestBody @Valid verificarCodDTO: VerificarCodDTO) {
        usuarioService.verificarCod(
            cod = verificarCodDTO.cod.toInt(),
            email = verificarCodDTO.email
        )
    }

    @PutMapping("/alterar-senha")
    @Transactional
    @CacheEvict(value = [CacheConstants.USUARIOS_CACHE], allEntries = true)
    fun alterarSenha(
        @RequestBody @Valid resetSenhaDTO: ResetSenhaDTO
    ) {
        usuarioService.alterarSenha(
            email = resetSenhaDTO.email,
            senha = resetSenhaDTO.senha,
            cod = resetSenhaDTO.cod
        )
    }
}