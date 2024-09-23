package com.ricky.adocao.dto

data class FiltroSearchDTO(
    val isDog: Boolean = false,
    val isCat: Boolean = false,
    val isAchado: Boolean = false,
    val isAdotar: Boolean = false,
    val isPerdido: Boolean = false,
    val isGrande: Boolean = false,
    val isMedio: Boolean = false,
    val isPequeno: Boolean = false,
    val isMacho: Boolean = false,
    val isFemea: Boolean = false,
    val isFilhote:Boolean = false,
    val isJovem:Boolean = false,
    val isAdulto:Boolean = false,
    val isIdoso:Boolean = false,
)
