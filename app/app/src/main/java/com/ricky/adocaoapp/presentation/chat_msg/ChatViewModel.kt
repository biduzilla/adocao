package com.ricky.adocaoapp.presentation.chat_msg

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ricky.adocaoapp.data.network.websocket.GetMessage
import com.ricky.adocaoapp.data.network.websocket.SendMessage
import com.ricky.adocaoapp.domain.models.Msg
import com.ricky.adocaoapp.domain.models.SendChatMessage
import com.ricky.adocaoapp.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val sendMessage: SendMessage,
    private val getMessage: GetMessage,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val _state = MutableStateFlow(ChatState())
    val state = _state.asStateFlow()

    init {
        savedStateHandle.get<String>(Constants.PARAM_RECEIVER_ID)?.let { recipientId ->
            _state.update {
                it.copy(
                    recipientId = recipientId
                )
            }
        }

        savedStateHandle.get<String>(Constants.PARAM_RECEIVER_NOME)?.let { recipientNome ->
            _state.update {
                it.copy(
                    recipientNome = recipientNome
                )
            }
        }
        loadNotification()
    }

    private fun loadNotification() {
        viewModelScope.launch {
            getMessage(_state.value.senderId).collect { noti ->
                val msgs = _state.value.msgs.toMutableList()
                msgs.add(
                    Msg(
                        content = noti.content,
                        isFromMe = false
                    )
                )
                _state.update {
                    it.copy(
                        msgs = msgs
                    )
                }
            }
        }
    }

    fun onEvent(event: ChatEvent) {
        when (event) {
            is ChatEvent.OnChangeMsg -> {
                _state.update {
                    it.copy(
                        msg = event.msg
                    )
                }
            }

            ChatEvent.SendMsg -> {
                if (_state.value.msg.trim().isBlank()) {
                    return
                }

                sendMessage(
                    SendChatMessage(
                        senderId = _state.value.senderId,
                        recipientId = _state.value.recipientId,
                        content = _state.value.msg,
                    )
                ).launchIn(viewModelScope)

                val msgs = _state.value.msgs.toMutableList()
                msgs.add(
                    Msg(
                        content = _state.value.msg,
                        isFromMe = true
                    )
                )
                _state.update {
                    it.copy(
                        msgs = msgs,
                        msg = "",
                    )
                }
            }
        }
    }
}