package com.ricky.adocaoapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ricky.adocaoapp.data.local.DataStoreUtil
import com.ricky.adocaoapp.domain.models.FiltroSearch
import com.ricky.adocaoapp.domain.use_case.PetManager
import com.ricky.adocaoapp.utils.Resource
import com.ricky.adocaoapp.utils.calcularDistancia
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val petManager: PetManager,
    private val dataStoreUtil: DataStoreUtil
) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    init {
        getLoc()
    }

    private fun getLoc() {
        viewModelScope.launch {
            dataStoreUtil.getLat().collect { lat ->
                _state.update {
                    it.copy(
                        lat = lat,
                    )
                }
            }
        }
        viewModelScope.launch {
            dataStoreUtil.getLong().collect { long ->
                _state.update {
                    it.copy(
                        long = long,
                    )
                }

            }
        }
    }

    private fun loadMore(isFiltro: Boolean) {
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
                            val newPetsList = if (isFiltro) {
                                pagePet.content
                            } else {
                                _state.value.pets + pagePet.content
                            }

                            it.copy(
                                pets = newPetsList,
                                loadMoreVisible = newPetsList.size < pagePet.totalElements
                            )
                        }

                        setDistancia()
                    }
                    _state.value = _state.value.copy(
                        isLoading = false,
                    )
                }
            }
        }.launchIn(viewModelScope)

    }

    private fun setDistancia() {
        val updatedPets = _state.value.pets.map { pet ->
            pet.copy(
                distancia = "%.2f km".format(
                    calcularDistancia(
                        lat1 = _state.value.lat,
                        lon1 = _state.value.long,
                        lat2 = pet.lat,
                        lon2 = pet.long
                    )
                )
            )
        }

        _state.update {
            it.copy(pets = updatedPets)
        }
    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.OnChangeFiltro -> {
                _state.update {
                    it.copy(
                        filtroSearch = event.filtro
                    )
                }
                loadMore(true)
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
                loadMore(false)
            }

            HomeEvent.OnSearch -> {
                loadMore(true)
            }

            HomeEvent.ClearError -> {
                _state.update {
                    it.copy(
                        error = ""
                    )
                }
            }

            HomeEvent.ClearFiltro -> {
                _state.update {
                    it.copy(
                        filtroSearch = FiltroSearch()
                    )
                }
            }

            HomeEvent.Resume -> {
                _state.update {
                    it.copy(
                        page = 0,
                        pets = emptyList()
                    )
                }
                loadMore(true)
            }
        }
    }
}