package com.ricky.adocaoapp.domain.models

import java.time.Instant

data class ChatMessage(
    val id: String,
    val chatRoom: ChatRoom = ChatRoom(),
    val senderId: String,
    val recipientId: String,
    val content: String,
    val timestamp: Instant,
)
