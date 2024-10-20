package com.ricky.adocao.models

import jakarta.persistence.*
import lombok.Data

@Data
@Entity
@Table(name = "CHAT_ROOM")
data class ChatRoom(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "CHAT_ROOM_ID")
    var id: String = "",
    @Column(name = "CHAT_ID")
    var chatId: String = "",
    @Column(name = "SENDER_ID")
    var senderId: String = "",
    @Column(name = "RECIPIENT_ID")
    var recipientId: String = ""
)