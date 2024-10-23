package com.ricky.adocaoapp.data.repository

import com.ricky.adocaoapp.data.network.api.ChatApi
import com.ricky.adocaoapp.data.network.api.PetApi
import com.ricky.adocaoapp.domain.models.ChatMessage
import com.ricky.adocaoapp.domain.models.FiltroSearch
import com.ricky.adocaoapp.domain.models.PagePet
import com.ricky.adocaoapp.domain.models.Pet
import com.ricky.adocaoapp.domain.models.PetRequest
import com.ricky.adocaoapp.domain.repository.ChatRepository
import com.ricky.adocaoapp.domain.repository.PetRepository
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