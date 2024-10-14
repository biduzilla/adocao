package com.ricky.adocaoapp.presentation.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ricky.adocaoapp.data.local.DataStoreUtil
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
    private val dataStoreUtil: DataStoreUtil,
) :
    ViewModel() {
    private val _state = MutableStateFlow(DetailsState())
    val state = _state.asStateFlow()

    init {
        getLoc()
        saveStateHandle.get<String>(Constants.PARAM_PET_ID)?.let { petId ->
            loadPet(petId)
        }

        viewModelScope.launch {
            dataStoreUtil.getToken().collect {
                _state.update {
                    it.copy(
                        userId = it.userId
                    )
                }
            }
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

                val pet = _state.value.pet
                pet.distancia = "%.2f km".format(
                    calcularDistancia(
                        lat1 = _state.value.lat,
                        lon1 = _state.value.lat,
                        lat2 = pet.lat,
                        lon2 = pet.long
                    )
                )

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