package com.ricky.adocaoapp.data.network.websocket

import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializer
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializer
import com.ricky.adocaoapp.domain.models.SendChatMessage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.hildan.krossbow.stomp.StompSession
import org.hildan.krossbow.stomp.sendText
import java.time.Instant
import javax.inject.Inject

class SendMessage @Inject constructor(private val session: StompSession) {
    operator fun invoke(sendChatMessage: SendChatMessage): Flow<Boolean> = flow {
        val gson: Gson = GsonBuilder()
            .registerTypeAdapter(Instant::class.java, JsonSerializer<Instant> { src, _, _ ->
                src?.let {
                    JsonPrimitive(it.toString())
                }
            })
            .registerTypeAdapter(Instant::class.java, JsonDeserializer { json, _, _ ->
                Instant.parse(json.asString)
            })
            .create()
        try {
            Log.i("infoteste", "SendMessage: $sendChatMessage")
            val jsonMessage = gson.toJson(sendChatMessage)
            Log.i("infoteste", "SendMessage: $jsonMessage")
            session.sendText("/app/chat", jsonMessage)
        } catch (e: Exception) {
        }
    }
}
