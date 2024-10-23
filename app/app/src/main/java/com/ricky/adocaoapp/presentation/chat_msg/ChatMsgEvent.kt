package com.ricky.adocaoapp.presentation.chat_msg

sealed interface ChatMsgEvent {
    data class OnChangeMsg(val msg:String): ChatMsgEvent
    data object SendMsg: ChatMsgEvent
    data object ClearError : ChatMsgEvent
    data object Resume : ChatMsgEvent
    data object Disconnect : ChatMsgEvent

}