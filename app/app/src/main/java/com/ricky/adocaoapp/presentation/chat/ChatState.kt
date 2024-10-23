package com.ricky.adocaoapp.presentation.chat

import com.ricky.adocaoapp.domain.models.Usuario

data class ChatState(
    var users: List<Usuario> = emptyList(),
    val error: String = "",
    val isLoading: Boolean = false,
    val idUser:String = ""
    )
