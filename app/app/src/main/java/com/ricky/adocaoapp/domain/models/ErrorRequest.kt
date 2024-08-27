package com.ricky.adocaoapp.domain.models

import java.time.LocalDateTime

data class ErrorRequest(
    val timestamp: LocalDateTime = LocalDateTime.now(),
    val status: Int,
    val error: String,
    val message: String?,
    val path: String
)
