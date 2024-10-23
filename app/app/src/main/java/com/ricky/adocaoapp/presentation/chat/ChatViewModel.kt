package com.ricky.adocaoapp.presentation.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ricky.adocaoapp.data.local.DataStoreUtil
import com.ricky.adocaoapp.domain.use_case.UserManager
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
class ChatViewModel @Inject constructor(
    private val userManager: UserManager,
    private val dataStoreUtil: DataStoreUtil
) : ViewModel() {
    private val _state = MutableStateFlow(ChatState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            dataStoreUtil.getToken().collect { token ->
                token?.let {
                    _state.update {
                        it.copy(
                            idUser = token.idUser
                        )
                    }
                }
            }
        }
    }

    fun onEvent(event: ChatEvent) {
        when (event) {
            ChatEvent.ClearError -> {
                _state.update {
                    it.copy(
                        error = ""
                    )
                }
            }

            ChatEvent.Resume -> {
                loadUsers(_state.value.idUser)
            }
        }
    }

    private fun loadUsers(userId: String) {
        userManager.getUsuariosBySenderId(userId).onEach { result ->
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
                    result.data?.let { users ->
                        _state.update {
                            it.copy(
                                users = users,
                                isLoading = false,
                            )
                        }
                    }
                }
            }
        }.launchIn(viewModelScope)
    }
}