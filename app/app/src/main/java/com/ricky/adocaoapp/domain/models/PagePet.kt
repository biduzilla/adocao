package com.ricky.adocaoapp.domain.models

data class PagePet(
    val content: List<Pet>,
    val pageNumber: Int,
    val pageSize: Int,
    val totalElements: Long,
    val totalPages: Int,
    val isLast: Boolean
)