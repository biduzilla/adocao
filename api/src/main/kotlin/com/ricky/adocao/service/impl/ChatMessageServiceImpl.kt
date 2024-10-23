package com.ricky.adocao.service.impl

import com.ricky.adocao.models.ChatMessage
import com.ricky.adocao.repository.ChatMessageRepository
import com.ricky.adocao.service.ChatMessageService
import com.ricky.adocao.service.ChatRoomService
import org.springframework.stereotype.Service

@Service
class ChatMessageServiceImpl(
    private val chatMessageRepository: ChatMessageRepository,
    private val chatRoomService: ChatRoomService
) : ChatMessageService {
    override fun save(chatMessage: ChatMessage): ChatMessage {
        val chatId = chatRoomService.getChatRoomId(
            senderId = chatMessage.senderId,
            recipientId = chatMessage.recipientId,
            createNewRoomIfNotExists = true
        ).orElseThrow()

        val chatRoom = chatRoomService.findByChatIdAndSenderIdAndRecipientId(
            chatId = chatId,
            senderId = chatMessage.senderId,
            recipientId = chatMessage.recipientId
        )

        chatMessage.chatRoom = chatRoom
        return chatMessageRepository.save(chatMessage)
    }

    override fun findChatMessage(senderId: String, recipientId: String): List<ChatMessage> {
        val chatId = chatRoomService.getChatRoomId(
            senderId = senderId,
            recipientId = recipientId,
            createNewRoomIfNotExists = false
        )
        return chatId.map(chatMessageRepository::findByChatId).orElse(listOf());
    }

    override fun findBySenderIdAndReceiverId(senderId: String, receiverId: String): List<ChatMessage> {
        return chatMessageRepository.findBySenderIdAndReceiverId(
            senderId,
            receiverId
        )
    }
}