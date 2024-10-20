package com.ricky.adocaoapp.presentation.config

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ricky.adocaoapp.data.local.DataStoreUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ConfigViewModel @Inject constructor(private val dataStoreUtil: DataStoreUtil) : ViewModel() {
    private val _state = MutableStateFlow(ConfigState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            dataStoreUtil.getToken().collect { token ->
                token?.let {
                    _state.update {
                        it.copy(
                            userId = token.idUser
                        )
                    }
                }
            }
        }
        viewModelScope.launch {
            dataStoreUtil.getTheme().collect { isDark ->
                _state.update {
                    it.copy(
                        isDark = isDark
                    )
                }
            }
        }
    }

    fun onEvent(event: ConfigEvent) {
        when (event) {
            ConfigEvent.ChangeTheme -> {
                viewModelScope.launch {
                    dataStoreUtil.saveTheme(!_state.value.isDark)
                }
            }
        }
    }
}