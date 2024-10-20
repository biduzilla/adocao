package com.ricky.adocaoapp.domain.models

import java.time.LocalDate

data class ChatMessage(
    var id: String = "",
    var chatRoom: ChatRoom = ChatRoom(),
    var senderId: String = "",
    var recipientId: String = "",
    var content: String = "",
    var timestamp: String = ""
)
