package com.ricky.adocao.controller

import com.ricky.adocao.dto.FiltroSearchDTO
import com.ricky.adocao.dto.PetDTO
import com.ricky.adocao.mapper.PetDTOMapper
import com.ricky.adocao.mapper.PetMapper
import com.ricky.adocao.service.PetService
import com.ricky.adocao.utils.CacheConstants
import jakarta.transaction.Transactional
import jakarta.validation.Valid
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/pet")
class PetController(
    private val petService: PetService,
    private val petDTOMapper: PetDTOMapper,
    private val petMapper: PetMapper
) {

    @GetMapping
    @Cacheable(CacheConstants.PET_CACHE)
    fun findAll(
        @RequestParam(required = false, defaultValue = "0") page: Int,
        @RequestParam(required = false) search: String?,
        @RequestParam(required = false) orderBy: String?,
        @RequestParam(defaultValue = "15") qtd: Int,
        @RequestParam(defaultValue = "false")isDog:Boolean,
        @RequestParam(defaultValue = "false") isCat:Boolean,
        @RequestParam(defaultValue = "false") isAchado:Boolean,
        @RequestParam(defaultValue = "false") isAdotar:Boolean,
        @RequestParam(defaultValue = "false") isPerdido:Boolean,
        @RequestParam(defaultValue = "false") isGrande:Boolean,
        @RequestParam(defaultValue = "false") isMedio:Boolean,
        @RequestParam(defaultValue = "false") isPequeno:Boolean,
        @RequestParam(defaultValue = "false") isMacho:Boolean,
        @RequestParam(defaultValue = "false") isFemea:Boolean,
        @RequestParam(defaultValue = "false") isFilhote:Boolean,
        @RequestParam(defaultValue = "false") isAdulto:Boolean,
        @RequestParam(defaultValue = "false") isIdoso:Boolean,
    ): Page<PetDTO> {
        val filtros = FiltroSearchDTO(
            isDog = isDog,
            isCat = isCat,
            isAchado = isAchado,
            isAdotar = isAdotar,
            isPerdido = isPerdido,
            isGrande = isGrande,
            isMedio = isMedio,
            isPequeno = isPequeno,
            isMacho = isMacho,
            isFemea = isFemea,
            isFilhote = isFilhote,
            isAdulto = isAdulto,
            isIdoso = isIdoso
        )
        val pageable = petService.findAll(
            search = search,
            orderBy = orderBy,
            page = page,
            qtd = qtd,
            filtro = filtros
        )
        return pageable.map { petDTOMapper.map(it) }
    }

    @GetMapping("/{petId}")
    fun findById(@PathVariable petId: String): PetDTO {
        return petDTOMapper.map(petService.findById(petId))
    }

    @PostMapping
    @CacheEvict(value = [CacheConstants.PET_CACHE], allEntries = true)
    @Transactional
    fun insert(@RequestBody @Valid petDTO: PetDTO): ResponseEntity<PetDTO> {
        val pet = petService.save(
            pet = petMapper.map(petDTO),
            userId = petDTO.donoId
        )
        return ResponseEntity.status(HttpStatus.CREATED).body(petDTOMapper.map(pet))
    }

    @PutMapping
    @CacheEvict(value = [CacheConstants.PET_CACHE], allEntries = true)
    @Transactional
    fun update(@RequestBody @Valid petDTO: PetDTO): ResponseEntity<PetDTO> {
        val pet = petService.update(petMapper.map(petDTO))
        return ResponseEntity.ok(petDTOMapper.map(pet))
    }

    @DeleteMapping("/{petId}")
    @CacheEvict(value = [CacheConstants.PET_CACHE], allEntries = true)
    fun deleteById(@PathVariable petId: String) {
        petService.deleteById(petId)
    }
}