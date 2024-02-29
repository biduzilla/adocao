package com.ricky.adocao.controller

import com.ricky.adocao.dto.UsuarioDTO
import com.ricky.adocao.mapper.UsuarioDTOMapper
import com.ricky.adocao.mapper.UsuarioMapper
import com.ricky.adocao.service.UsuarioService
import com.ricky.adocao.utils.CacheConstants
import jakarta.transaction.Transactional
import jakarta.validation.Valid
import org.springframework.beans.BeanUtils
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
class UsuarioController(
    private val usuarioService: UsuarioService,
    private val usuarioDTOMapper: UsuarioDTOMapper,
    private val usuarioMapper: UsuarioMapper,
) {

    @GetMapping
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

    @GetMapping("/{idUser}")
    fun findById(@PathVariable idUser: String): UsuarioDTO {
        return usuarioDTOMapper.map(usuarioService.findById(idUser))
    }

    @PostMapping
    @Transactional
    @CacheEvict(value = [CacheConstants.USUARIOS_CACHE], allEntries = true)
    fun insert(@RequestBody @Valid usuarioDTO: UsuarioDTO): ResponseEntity<UsuarioDTO> {
        val userSalvar = usuarioMapper.map(usuarioDTO)
        val user = usuarioService.save(userSalvar)
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioDTOMapper.map(user))
    }

    @PutMapping
    @Transactional
    @CacheEvict(value = [CacheConstants.USUARIOS_CACHE], allEntries = true)
    fun update(@RequestBody @Valid usuarioDTO: UsuarioDTO): ResponseEntity<UsuarioDTO> {
        val usuario = usuarioService.update(usuarioMapper.map(usuarioDTO))
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioDTOMapper.map(usuario))
    }

    @DeleteMapping("{idUsuario}")
    @Transactional
    @CacheEvict(value = [CacheConstants.USUARIOS_CACHE], allEntries = true)
    fun deleteById(@PathVariable idUsuario: String) {
        usuarioService.deleteById(idUsuario)
    }


}