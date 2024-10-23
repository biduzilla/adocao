package com.ricky.adocao.models

import jakarta.persistence.*
import lombok.Data
import java.time.LocalDate
import java.util.*

@Data
@Entity
@Table(name = "CHAT_MESSAGE")
data class ChatMessage(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "CHAT_MESSAGE_ID")
    var id: String = "",
    @ManyToOne
    @JoinColumn(name = "CHAT_ROOM_ID")
    var chatRoom: ChatRoom,
    @Column(name = "SENDER_ID")
    var senderId: String = "",
    @Column(name = "RECIPIENT_ID")
    var recipientId: String = "",
    @Column(name = "CONTENT")
    var content: String = "",
    @Column(name = "TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    var timestamp: Date
)