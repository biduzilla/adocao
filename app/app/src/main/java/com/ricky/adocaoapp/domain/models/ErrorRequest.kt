package com.ricky.adocaoapp.domain.models

import java.time.LocalDateTime

data class ErrorRequest(
    val timestamp: String,
    val status: Int,
    val error: String,
    val message: String?,
    val path: String
)


