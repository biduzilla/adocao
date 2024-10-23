package com.ricky.adocaoapp.domain.repository

import com.ricky.adocaoapp.domain.models.ChatMessage
import com.ricky.adocaoapp.domain.models.FiltroSearch
import com.ricky.adocaoapp.domain.models.PagePet
import com.ricky.adocaoapp.domain.models.Pet
import com.ricky.adocaoapp.domain.models.PetRequest
import retrofit2.Response
import retrofit2.http.Path

interface ChatRepository {
    suspend fun getMessages(
        senderId: String,
        recipientId: String
    ): Response<List<ChatMessage>>
}