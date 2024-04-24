package com.ricky.adocao.service

import com.ricky.adocao.models.ChatRoom
import java.util.*

interface ChatRoomService {

    fun getChatRoomId(
        senderId: String,
        recipientId: String,
        createNewRoomIfNotExists: Boolean
    ): Optional<String>

    fun findByChatIdAndSenderIdAndRecipientId(
        chatId: String,
        senderId: String,
        recipientId: String
    ): ChatRoom

    fun createChatId(
        senderId: String,
        recipientId: String
    ): String
}