package com.ricky.adocaoapp.domain.repository

import com.ricky.adocaoapp.domain.models.ChatMessage
import retrofit2.Response

interface ChatRepository {
    suspend fun getMessages(
        senderId: String,
        recipientId: String
    ): Response<List<ChatMessage>>
}