package com.ricky.adocaoapp.presentation.chat

import com.ricky.adocaoapp.presentation.chat_msg.ChatMsgEvent

sealed interface ChatEvent {
    data object ClearError : ChatEvent
    data object Resume : ChatEvent
}