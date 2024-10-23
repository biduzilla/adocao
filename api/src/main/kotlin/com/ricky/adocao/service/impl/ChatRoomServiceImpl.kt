package com.ricky.adocao.service.impl

import com.ricky.adocao.models.ChatRoom
import com.ricky.adocao.repository.ChatRoomRepository
import com.ricky.adocao.service.ChatRoomService
import org.springframework.stereotype.Service
import java.util.*

@Service
class ChatRoomServiceImpl(private val chatRoomRepository: ChatRoomRepository) : ChatRoomService {
    override fun getChatRoomId(
        senderId: String,
        recipientId: String,
        createNewRoomIfNotExists: Boolean
    ): Optional<String> {
        return chatRoomRepository.findBySenderIdAndRecipientId(
            senderId = senderId,
            recipientId = recipientId
        )
            .map(ChatRoom::chatId)
            .or {
                if (createNewRoomIfNotExists) {
                    val chatId = createChatId(senderId, recipientId)
                    return@or Optional.of(chatId)
                }
                return@or Optional.empty()
            }
    }

    override fun findByChatIdAndSenderIdAndRecipientId(
        chatId: String,
        senderId: String,
        recipientId: String
    ): ChatRoom {
        return chatRoomRepository.findByChatIdAndSenderIdAndRecipientId(
            chatId = chatId,
            senderId = senderId,
            recipientId = recipientId
        ).orElseThrow()
    }

    override fun createChatId(senderId: String, recipientId: String): String {
        val chatId = String.format("%s_%s", senderId, recipientId)

        val senderRecipient = ChatRoom(
            chatId = chatId,
            senderId = senderId,
            recipientId = recipientId,
        )

        val recipientSender = ChatRoom(
            chatId = chatId,
            recipientId = senderId,
            senderId = recipientId
        )

        chatRoomRepository.saveAll(listOf(senderRecipient, recipientSender))

        return chatId
    }
}