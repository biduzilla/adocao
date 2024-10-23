package com.ricky.adocao.controller

import com.ricky.adocao.models.ChatMessage
import com.ricky.adocao.models.ChatNotification
import com.ricky.adocao.service.ChatMessageService
import org.springframework.http.ResponseEntity
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/messages")
@CrossOrigin
class ChatController(
    private val simpMessagingTemplate: SimpMessagingTemplate,
    private val chatMessageService: ChatMessageService
) {
    @MessageMapping("/chat")
    fun processMessage(@Payload chatMessage: ChatMessage) {
        val saveMsg = chatMessageService.save(chatMessage)
        simpMessagingTemplate.convertAndSendToUser(
            chatMessage.recipientId,
            "/queue/messages",
            ChatNotification(
                id = saveMsg.id,
                senderId = saveMsg.senderId,
                recipientId = saveMsg.recipientId,
                content = saveMsg.content
            )
        )
    }

    @GetMapping("/{senderId}/{recipientId}")
    fun findChatMessage(
        @PathVariable senderId: String,
        @PathVariable recipientId: String
    ): ResponseEntity<List<ChatMessage>> {
        return ResponseEntity.ok(
            chatMessageService.findChatMessage(
                senderId = senderId,
                recipientId = recipientId
            )
        )
    }
}