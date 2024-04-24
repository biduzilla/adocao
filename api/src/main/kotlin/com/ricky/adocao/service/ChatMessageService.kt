package com.ricky.adocao.service

import com.ricky.adocao.models.ChatMessage

interface ChatMessageService {
    fun save(chatMessage: ChatMessage): ChatMessage
    fun findChatMessage(
        senderId: String,
        recipientId: String
    ): List<ChatMessage>
}