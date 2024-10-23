package com.ricky.adocaoapp.presentation.chat_msg

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ricky.adocaoapp.data.local.DataStoreUtil
import com.ricky.adocaoapp.data.network.websocket.GetMessage
import com.ricky.adocaoapp.data.network.websocket.SendMessage
import com.ricky.adocaoapp.domain.models.Msg
import com.ricky.adocaoapp.domain.models.SendChatMessage
import com.ricky.adocaoapp.domain.use_case.ChatCaseGetAll
import com.ricky.adocaoapp.utils.Constants
import com.ricky.adocaoapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatMsgViewModel @Inject constructor(
    private val sendMessage: SendMessage,
    private val getMessage: GetMessage,
    private val savedStateHandle: SavedStateHandle,
    private val chatCaseGetAll: ChatCaseGetAll,
    private val dataStoreUtil: DataStoreUtil
) : ViewModel() {
    private val _state = MutableStateFlow(ChatMsgState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            dataStoreUtil.getToken().collect { token ->
                token?.let {
                    _state.update {
                        it.copy(
                            senderId = token.idUser
                        )
                    }
                    receiveNotification(token.idUser)
                }
            }
        }

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
    }

    private fun loadMsgs() {
        chatCaseGetAll.invoke(
            senderId = _state.value.senderId,
            recipientId = _state.value.recipientId
        ).onEach { result ->
            when (result) {
                is Resource.Error -> {
                    _state.update { state ->
                        state.copy(
                            isLoading = false,
                            error = result.message ?: "Erro inesperado"
                        )
                    }
                }

                is Resource.Loading -> {
                    _state.update { state ->
                        state.copy(
                            isLoading = true,
                        )
                    }
                }

                is Resource.Success -> {
                    result.data?.let { chatMessages ->
                        val msgs = chatMessages.map { cM ->
                            Msg(
                                content = cM.content,
                                isFromMe = _state.value.senderId == cM.senderId
                            )
                        }
                        _state.update { state ->
                            state.copy(
                                isLoading = false,
                                msgs = msgs
                            )
                        }

                    }
                }
            }

        }.launchIn(viewModelScope)
    }

    private fun receiveNotification(idUser:String) {
        viewModelScope.launch {
            getMessage(idUser).collect { noti ->
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

    fun onEvent(event: ChatMsgEvent) {
        when (event) {
            is ChatMsgEvent.OnChangeMsg -> {
                _state.update {
                    it.copy(
                        msg = event.msg
                    )
                }
            }

            ChatMsgEvent.SendMsg -> {
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

            ChatMsgEvent.ClearError -> {
                _state.update {
                    it.copy(
                        error = ""
                    )
                }
            }

            ChatMsgEvent.Resume -> {
                loadMsgs()
            }
        }
    }
}