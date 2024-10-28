package com.ricky.adocaoapp.data.repository

import com.ricky.adocaoapp.data.network.api.ChatApi
import com.ricky.adocaoapp.domain.models.ChatMessage
import com.ricky.adocaoapp.domain.repository.ChatRepository
import retrofit2.Response
import javax.inject.Inject

class ChatRepositoryImpl @Inject constructor(private val api: ChatApi) : ChatRepository {
    override suspend fun getMessages(
        senderId: String,
        recipientId: String
    ): Response<List<ChatMessage>> = api.getMessages(
        senderId = senderId,
        recipientId = recipientId
    )
}