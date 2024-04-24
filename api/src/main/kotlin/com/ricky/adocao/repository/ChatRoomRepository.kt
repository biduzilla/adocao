package com.ricky.adocao.repository

import com.ricky.adocao.models.ChatRoom
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface ChatRoomRepository : JpaRepository<ChatRoom, String> {
    fun findBySenderIdAndRecipientId(senderId: String, recipientId: String): Optional<ChatRoom>

    fun findByChatIdAndSenderIdAndRecipientId(chatId: String, senderId: String, recipientId: String): Optional<ChatRoom>

}