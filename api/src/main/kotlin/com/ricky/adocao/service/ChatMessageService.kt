package com.ricky.adocao.service

import com.ricky.adocao.models.ChatMessage
import org.springframework.data.repository.query.Param

interface ChatMessageService {
    fun save(chatMessage: ChatMessage): ChatMessage
    fun findChatMessage(
        senderId: String,
        recipientId: String
    ): List<ChatMessage>

    fun findBySenderIdAndReceiverId(
        senderId: String,
        receiverId: String
    ): List<ChatMessage>
}