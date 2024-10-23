package com.ricky.adocaoapp.presentation.chat_msg

sealed interface ChatEvent {
    data class OnChangeMsg(val msg:String): ChatEvent
    data object SendMsg: ChatEvent
}