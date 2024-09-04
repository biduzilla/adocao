package com.ricky.adocaoapp.presentation.auth.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ricky.adocaoapp.domain.models.Usuario
import com.ricky.adocaoapp.domain.use_case.UserManager
import com.ricky.adocaoapp.utils.Resource
import com.ricky.adocaoapp.utils.formatPhoneNumber
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val userCases: UserManager) : ViewModel() {

    private val _state = MutableStateFlow(RegisterState())
    val state = _state.asStateFlow()

    private fun createAccount() {
        val user = Usuario(
            nome = _state.value.nome,
            email = _state.value.email,
            senha = _state.value.senha,
            telefone = _state.value.telefone
        )

        userCases.save(user).onEach { result ->
            when (result) {
                is Resource.Error -> {
                    _state.update {
                        it.copy(
                            loading = false,
                            error = result.message ?: "Error",
                        )
                    }
                }

                is Resource.Loading -> {
                    _state.update {
                        it.copy(
                            loading = true,
                        )
                    }
                }

                is Resource.Success -> {
                    _state.update {
                        it.copy(
                            loading = false,
                            createOk = true,
                        )
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

    fun onEvent(event: RegisterEvent) {
        when (event) {
            RegisterEvent.CreateAccount -> {
                if (_state.value.nome.trim().isBlank()) {
                    _state.update {
                        it.copy(
                            onErrorNome = true
                        )
                    }
                    return
                }
                if (_state.value.email.trim().isBlank()) {
                    _state.update {
                        it.copy(
                            onErrorEmail = true
                        )
                    }
                    return
                }
                if (_state.value.telefone.trim().isBlank()) {
                    _state.update {
                        it.copy(
                            onErrorTelefone = true
                        )
                    }
                    return
                }
                if (_state.value.senha.trim().isBlank()) {
                    _state.update {
                        it.copy(
                            onErrorSenha = true
                        )
                    }
                    return
                }
                if (_state.value.confirmarSenha.trim()
                        .isBlank() || _state.value.confirmarSenha != _state.value.senha
                ) {
                    _state.update {
                        it.copy(
                            onErrorConfirmarSenha = true
                        )
                    }
                    return
                }
                createAccount()
            }

            is RegisterEvent.OnChangeConfirmSenha -> {
                _state.update {
                    it.copy(
                        confirmarSenha = event.senha,
                        onErrorConfirmarSenha = false
                    )
                }
            }

            is RegisterEvent.OnChangeEmail -> {
                _state.update {
                    it.copy(
                        email = event.email,
                        onErrorEmail = false
                    )
                }
            }

            is RegisterEvent.OnChangeNome -> {
                _state.update {
                    it.copy(
                        nome = event.nome,
                        onErrorNome = false
                    )
                }
            }

            is RegisterEvent.OnChangeSenha -> {
                _state.update {
                    it.copy(
                        senha = event.senha,
                        onErrorSenha = false
                    )
                }
            }

            is RegisterEvent.OnChangeTelefone -> {
                _state.update {
                    it.copy(
                        telefone = formatPhoneNumber(event.telefone),
                        onErrorTelefone = false
                    )
                }
            }

            RegisterEvent.ClearError -> _state.update {
                it.copy(
                    error = ""
                )
            }
        }
    }
}