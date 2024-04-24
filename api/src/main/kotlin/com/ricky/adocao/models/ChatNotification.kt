package com.ricky.adocao.models

data class ChatNotification(
    var id: String = "",
    var senderId: String = "",
    var recipientId: String = "",
    var content: String = ""
)
