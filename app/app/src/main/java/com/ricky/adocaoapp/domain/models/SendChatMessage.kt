package com.ricky.adocaoapp.domain.models

import java.time.LocalDate

data class SendChatMessage(
    var senderId: String = "",
    var recipientId: String = "",
    var content: String = "",
    var timestamp: LocalDate = LocalDate.now()
)
