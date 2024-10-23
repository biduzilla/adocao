package com.ricky.adocaoapp.data.network.websocket

import android.util.Log
import com.google.gson.Gson
import com.ricky.adocaoapp.domain.models.ChatNotification
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.hildan.krossbow.stomp.StompSession
import org.hildan.krossbow.stomp.subscribeText
import javax.inject.Inject

class GetMessage {
    operator fun invoke(userId: String,session: StompSession): Flow<ChatNotification> = flow {
        try {
            Log.i("infoteste", "GetMessage IdUser: /user/$userId/queue/messages")
            session.subscribeText("/user/$userId/queue/messages").collect { msg ->
                Log.i("infoteste", "GetMessage: $msg")
                val notification = Gson().fromJson(msg, ChatNotification::class.java)
                notification?.let {
                    emit(it)
                }
                if (notification != null) {
                    return@collect
                }
            }
        } catch (e: Exception) {
            return@flow
        }
    }
}