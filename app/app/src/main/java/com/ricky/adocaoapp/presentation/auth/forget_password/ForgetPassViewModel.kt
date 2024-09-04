package com.ricky.adocaoapp.presentation.auth.forget_password

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ricky.adocaoapp.domain.models.ResetSenha
import com.ricky.adocaoapp.domain.models.VerificarCod
import com.ricky.adocaoapp.domain.use_case.UserManager
import com.ricky.adocaoapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForgetPassViewModel @Inject constructor(private val userManager: UserManager) : ViewModel() {

    private val _state = MutableStateFlow(ForgetPassState())
    val state = _state.asStateFlow()

    fun onEvent(event: ForgetPassEvent) {
        when (event) {
            is ForgetPassEvent.OnChangeCod -> {
                _state.update {
                    it.copy(
                        cod = event.cod,
                        onErrorCod = false
                    )
                }
            }

            is ForgetPassEvent.OnChangeConfirmSenha -> {
                _state.update {
                    it.copy(
                        confirmSenha = event.senha,
                        onErrorConfirmSenha = false
                    )
                }
            }

            is ForgetPassEvent.OnChangeEmail -> {
                _state.update {
                    it.copy(
                        email = event.email,
                        onErrorEmail = false
                    )
                }
            }

            is ForgetPassEvent.OnChangeSenha -> {
                _state.update {
                    it.copy(
                        senha = event.senha,
                        onErrorSenha = false
                    )
                }
            }

            ForgetPassEvent.OnSendCod -> {
                if (_state.value.cod.trim().isBlank()) {
                    _state.update {
                        it.copy(
                            onErrorCod = true
                        )
                    }
                    return
                }

                viewModelScope.launch {
                    val verifyCod = VerificarCod(
                        cod = _state.value.cod,
                        email = _state.value.email
                    )
                    userManager.verifyCod(verifyCod).onEach { result ->
                        when (result) {
                            is Resource.Error -> {
                                _state.update {
                                    it.copy(
                                        error = result.message ?: "",
                                        isLoading = false
                                    )
                                }
                            }

                            is Resource.Loading -> {
                                _state.update {
                                    it.copy(
                                        isLoading = true
                                    )
                                }
                            }

                            is Resource.Success -> {
                                _state.update {
                                    it.copy(
                                        isLoading = false,
                                        isCodVer = true
                                    )
                                }
                            }
                        }
                    }
                }
            }

            ForgetPassEvent.OnSendEmail -> {
                if (_state.value.email.trim().isBlank()) {
                    _state.update {
                        it.copy(
                            onErrorEmail = true
                        )
                    }
                    return
                }

                viewModelScope.launch {
                    userManager.resetPassword(_state.value.email).onEach { result ->
                        when (result) {
                            is Resource.Error -> {
                                _state.update {
                                    it.copy(
                                        error = result.message ?: "",
                                        isLoading = false
                                    )
                                }
                            }

                            is Resource.Loading -> {
                                _state.update {
                                    it.copy(
                                        isLoading = true
                                    )
                                }
                            }

                            is Resource.Success -> {
                                _state.update {
                                    it.copy(
                                        isLoading = false,
                                        isEmailSend = true
                                    )
                                }
                            }
                        }
                    }
                }
            }

            ForgetPassEvent.OnUpdatePassword -> {
                if (_state.value.senha.trim().isBlank()) {
                    _state.update {
                        it.copy(
                            onErrorSenha = true
                        )
                    }
                    return
                }

                if (_state.value.confirmSenha.trim()
                        .isBlank() || _state.value.senha != _state.value.confirmSenha
                ) {
                    _state.update {
                        it.copy(
                            onErrorConfirmSenha = true
                        )
                    }
                    return
                }

                val resetSenha = ResetSenha(
                    email = _state.value.email,
                    senha = _state.value.senha,
                    cod = _state.value.cod.toInt()
                )

                viewModelScope.launch {
                    userManager.changePassword(resetSenha).onEach { result ->
                        when (result) {
                            is Resource.Error -> {
                                _state.update {
                                    it.copy(
                                        error = result.message ?: "",
                                        isLoading = false
                                    )
                                }
                            }

                            is Resource.Loading -> {
                                _state.update {
                                    it.copy(
                                        isLoading = true
                                    )
                                }
                            }

                            is Resource.Success -> {
                                _state.update {
                                    it.copy(
                                        isLoading = false,
                                        isOk = true
                                    )
                                }
                            }
                        }
                    }
                }
            }

            ForgetPassEvent.ClearError -> TODO()
        }
    }
}