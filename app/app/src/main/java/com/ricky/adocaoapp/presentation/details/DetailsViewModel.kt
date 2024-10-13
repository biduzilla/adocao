package com.ricky.adocaoapp.presentation.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.climacompose.domain.location.LocationTracker
import com.ricky.adocaoapp.domain.use_case.UserManager
import com.ricky.adocaoapp.domain.use_case.pet.PetCaseGetById
import com.ricky.adocaoapp.utils.Constants
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
class DetailsViewModel @Inject constructor(
    private val petCaseGetById: PetCaseGetById,
    private val userManager: UserManager,
    private val saveStateHandle: SavedStateHandle,
    private val locationTracker: LocationTracker
) :
    ViewModel() {
    private val _state = MutableStateFlow(DetailsState())
    val state = _state.asStateFlow()

    init {
        getLoc()
        saveStateHandle.get<String>(Constants.PARAM_PET_ID)?.let { petId ->
            loadPet(petId)
        }
    }

    fun onEvent(event: DetailsEvent) {
        when (event) {
            DetailsEvent.ClearError -> {
                _state.value = DetailsState(
                    error = ""
                )
            }
        }
    }

    private fun getLoc() {
        viewModelScope.launch {
            locationTracker.getCurrentLocation()?.let { location ->
                val pet = _state.value.pet
                pet.distancia = "%.2f km".format(
                    calcularDistancia(
                        lat1 = location.latitude,
                        lon1 = location.longitude,
                        lat2 = pet.lat,
                        lon2 = pet.long
                    )
                )
                _state.update {
                    it.copy(
                        pet = pet
                    )
                }
            } ?: kotlin.run {
                _state.update {
                    it.copy(
                        isLoading = false,
                        error = "Não consegui receber sua posição atual. Tenha certeza que a permissão de acessar a localização está garantida"
                    )
                }
            }
        }

    }

    private fun loadPet(petId: String) {
        petCaseGetById(petId).onEach { result ->
            when (result) {
                is Resource.Error -> {
                    _state.value = DetailsState(
                        isLoading = false,
                        error = result.message ?: "Error Inesperado"
                    )
                }

                is Resource.Loading -> {
                    _state.value = DetailsState(
                        isLoading = true
                    )
                }

                is Resource.Success -> {
                    result.data?.let {
                        _state.value = DetailsState(
                            isLoading = false,
                            pet = result.data,
                        )
                    }
                }
            }
        }.launchIn(viewModelScope)
    }
}