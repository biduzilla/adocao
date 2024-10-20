package com.ricky.adocaoapp.data.network.api

import com.ricky.adocaoapp.domain.models.ChatMessage
import com.ricky.adocaoapp.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ChatApi {
    @GET("${Constants.MESSAGE_ENDPOINT}/{senderId}/{recipientId}")
    suspend fun getMessages(
        @Path("senderId") senderId: String,
        @Path("recipientId") recipientId: String
    ): Response<List<ChatMessage>>
}