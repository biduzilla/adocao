package com.ricky.adocaoapp.presentation.chat_msg

import com.ricky.adocaoapp.domain.models.Msg

data class ChatMsgState(
    val msgs: List<Msg> = emptyList(),
    val msg: String = "",
    val senderId: String = "",
    val recipientId: String = "",
    val recipientNome: String = "",
    val error: String = "",
    val isLoading: Boolean = false,
    )
