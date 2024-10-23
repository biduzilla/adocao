package com.ricky.adocao.repository

import com.ricky.adocao.models.ChatMessage
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface ChatMessageRepository : JpaRepository<ChatMessage, String> {
    @Query(value = "SELECT cm FROM ChatMessage cm WHERE cm.chatRoom.chatId = :chatId")
    fun findByChatId(@Param("chatId") chatId: String): List<ChatMessage>

    @Query(value = "SELECT cm FROM ChatMessage cm WHERE cm.senderId = :senderId and cm.recipientId = :receiverId")
    fun findBySenderIdAndReceiverId(@Param("senderId") senderId: String,@Param("receiverId") receiverId: String): List<ChatMessage>
}