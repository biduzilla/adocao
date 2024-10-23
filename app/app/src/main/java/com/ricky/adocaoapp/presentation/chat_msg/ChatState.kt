package com.ricky.adocaoapp.presentation.chat_msg

import com.ricky.adocaoapp.domain.models.Msg

data class ChatState(
    val msgs: List<Msg> = emptyList(),
    val msg: String = "",
    val senderId: String = "40510f35-6ba7-4088-b6ea-2bb650a59780",
    val recipientId: String = "f0431883-ffe3-48cf-8cfb-53f8adc06126",
    val recipientNome: String = "teste1",

    )
