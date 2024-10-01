package com.ricky.adocaoapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ricky.adocaoapp.domain.use_case.PetManager
import com.ricky.adocaoapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val petManager: PetManager
) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    init {
        loadMore()
    }

    private fun loadMore() {
        petManager.getAll(
            page = _state.value.page,
            search = _state.value.search,
            filtros = _state.value.filtroSearch
        ).onEach { result ->
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
                    result.data?.let { pagePet ->
                        _state.update {
                            it.copy(
                                pets = pagePet.content
                            )
                        }
                    }
                    _state.value = _state.value.copy(
                        isLoading = false,
                    )
                }
            }
        }.launchIn(viewModelScope)

    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.OnChangeFiltro -> {
                _state.update {
                    it.copy(
                        filtroSearch = event.filtro
                    )
                }
            }

            is HomeEvent.OnChangePesquisa -> {
                _state.update {
                    it.copy(
                        search = event.pesquisa
                    )
                }
            }

            HomeEvent.OnLoadMore -> {
                _state.update {
                    it.copy(
                        page = _state.value.page + 1
                    )
                }
                loadMore()
            }

            HomeEvent.OnSearch -> {
                loadMore()
            }

            HomeEvent.ClearError -> {
                _state.update {
                    it.copy(
                        error = ""
                    )
                }
            }
        }
    }
}