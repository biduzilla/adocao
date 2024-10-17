package com.ricky.adocaoapp.presentation.details

import android.util.Log
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
            dataStoreUtil.getToken().collect { token ->
                token?.let {
                    _state.update {
                        it.copy(
                            userId = token.idUser
                        )
                    }
                }
                checkIfIsDono()
            }
        }
    }

    fun onEvent(event: DetailsEvent) {
        when (event) {
            DetailsEvent.ClearError -> {
                _state.update { state ->
                    state.copy(
                        error = ""
                    )
                }
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
                updateDistancia()
            }
        }
        viewModelScope.launch {
            dataStoreUtil.getLong().collect { long ->
                _state.update {
                    it.copy(
                        long = long,
                    )
                }

                updateDistancia()
            }
        }
    }

    private fun loadPet(petId: String) {
        petCaseGetById(petId).onEach { result ->
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
                    result.data?.let { pet ->
                        _state.update { state ->
                            state.copy(
                                isLoading = false,
                                pet = pet
                            )
                        }
                        checkIfIsDono()
                        updateDistancia()
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun checkIfIsDono() {
        val userId = _state.value.userId
        val donoId = _state.value.pet.donoId

        if (userId != null && userId == donoId) {
            _state.update { state ->
                state.copy(isDono = true)
            }
        }
    }

    private fun updateDistancia() {
        val lat1 = _state.value.lat
        val lon1 = _state.value.long
        val pet = _state.value.pet

        if (lat1 != null && lon1 != null && pet.lat != null && pet.long != null) {
            val distancia = calcularDistancia(
                lat1 = lat1,
                lon1 = lon1,
                lat2 = pet.lat,
                lon2 = pet.long
            )
            _state.update { state ->
                state.copy(pet = pet.copy(distancia = "%.2f km".format(distancia)))
            }
        }
    }
}