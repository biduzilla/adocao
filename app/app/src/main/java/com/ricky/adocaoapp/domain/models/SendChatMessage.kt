package com.ricky.adocaoapp.domain.models

import java.time.Instant

data class SendChatMessage(
    var senderId: String = "",
    var recipientId: String = "",
    var content: String = "",
    var timestamp: Instant = Instant.now()
)
