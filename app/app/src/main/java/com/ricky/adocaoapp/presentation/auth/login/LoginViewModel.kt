package com.ricky.adocaoapp.presentation.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ricky.adocaoapp.domain.models.Login
import com.ricky.adocaoapp.domain.use_case.UserManager
import com.ricky.adocaoapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userCase: UserManager
) : ViewModel() {
    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    private fun login() {

        val login = Login(
            login = _state.value.email,
            senha = _state.value.senha
        )
        userCase.login(login).onEach { result ->
            when (result) {
                is Resource.Error -> {
                    _state.value = _state.value.copy(
                        isLoading = false,
                        error = result.message ?: "Error"
                    )
                }

                is Resource.Loading -> {
                    _state.value = _state.value.copy(
                        isLoading = true,
                    )
                }

                is Resource.Success -> {
                    _state.value = _state.value.copy(
                        isLoading = false,
                        onLogin = true
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.OnChangeEmail -> {
                _state.value = _state.value.copy(
                    email = event.email, onErrorEmail = false,
                )
            }

            LoginEvent.OnLogin -> {
                if (_state.value.email.trim().isBlank()) {
                    _state.value = _state.value.copy(
                        onErrorEmail = true,
                    )
                    return
                }

                if (_state.value.senha.trim().isBlank()) {
                    _state.value = _state.value.copy(
                        onErrorSenha = true,
                    )
                    return
                }
                viewModelScope.launch {
                    login()
                }
            }

            is LoginEvent.OnChangeSenha -> {
                _state.value = _state.value.copy(
                    senha = event.senha, onErrorSenha = false,
                )
            }
        }
    }
}