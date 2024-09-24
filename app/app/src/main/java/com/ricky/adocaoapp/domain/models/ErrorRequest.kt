package com.ricky.adocaoapp.domain.models

data class ErrorRequest(
    val timestamp: String,
    val status: Int,
    val error: String,
    val message: String?,
    val path: String
)


