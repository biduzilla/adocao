package com.ricky.adocaoapp.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ricky.adocaoapp.data.local.DataStoreUtil
import com.ricky.adocaoapp.domain.models.Token
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
class MainViewModel @Inject constructor(
    private val userManager: UserManager,
    private val dataStoreUtil: DataStoreUtil
) : ViewModel() {
    private val _state = MutableStateFlow(MainState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            dataStoreUtil.getToken().collect{
                it?.let {
                    loadUser(it.idUser)
                }
            }
        }
    }

    fun onEvent(event:MainEvent){
        when(event){
            MainEvent.ClearError -> {
                _state.update {
                    it.copy(
                        error=""
                    )
                }
            }
            MainEvent.OnSair -> {
                val token = Token()
                viewModelScope.launch {
                    dataStoreUtil.saveToken(token)
                    _state.update {
                        it.copy(
                            onSair = true
                        )
                    }
                }
            }
        }
    }

    private fun loadUser(idUser:String) {
        userManager.getById(idUser).onEach { result ->
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
                    result.data?.let { user ->
                        _state.update {
                            it.copy(
                                usuario = user,
                                isLoading = false
                            )
                        }

                    }
                }
            }
        }.launchIn(viewModelScope)
    }
}